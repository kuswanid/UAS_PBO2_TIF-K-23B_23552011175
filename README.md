# PropertEase – Kasir Penyewaan & Pengelolaan Properti
> Ujian Akhir Semester • Pemrograman Berorientasi Objek 2 (PBO2)
>
> ***TIF K 23B*** | ***Tatang*** | ***23552011175***

## 1. Deskripsi Singkat
**PropertEase** adalah aplikasi desktop berbasis **JavaFX** yang membantu pemilik usaha properti (kos-kosan, rumah kontrakan, apartemen, dll.) mencatat aset, penyewa, dan transaksi sewa.

Fitur utamanya:
- Manajemen aset properti
- Manajemen penyewa  
- Proses penyewaan dengan perhitungan total dan pajak otomatis  
- Autentikasi **Login / Register**  
- Dashboard & laporan transaksi

Aplikasi ini dirancang khusus untuk memenuhi studi kasus **“Kasir Properti”** pada UAS PBO2 dan diwajibkan menerapkan ke-4 pilar OOP (Inheritance, Polymorphism, Encapsulation, Abstraction).

## 2. Arsitektur & Teknologi
| Layer | Teknologi / Library |
|---------|---------------------|
| UI      | JavaFX 21, FXML, CSS |
| Logic   | Java 21, Pattern MVC |
| DB      | PostgreSQL via JDBC |
| Build   | Gradle Wrapper |

## 3. Struktur proyek
```bash
Propertease/
  ├─src/
    ├─ main/
    │ ├─ java/
    │ │ ├─ App.java
    │ │ ├─ models/
    │ │ ├─ repositories/
    │ │ ├─ services/
    │ │ ├─ utils/
    │ │ └─ views/
    │ └─ resources/
    │    └─ images
    └─ build.graddle.kts
```

## 4. Skema Database
```sql
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    email VARCHAR(255) NOT NULL UNIQUE,
    full_name VARCHAR(255) NOT NULL,
    password TEXT NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE tenants (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    address TEXT NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    full_name VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL
);

CREATE TABLE properties (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    address TEXT NOT NULL,
    description TEXT,
    name VARCHAR(255) NOT NULL,
    rent_price DOUBLE PRECISION NOT NULL,
    status VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL
);

CREATE TABLE leases (
    id SERIAL PRIMARY KEY,
    property_id INTEGER NOT NULL REFERENCES properties(id) ON DELETE CASCADE,
    tenant_id INTEGER NOT NULL REFERENCES tenants(id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    duration INTEGER NOT NULL,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE payments (
    id SERIAL PRIMARY KEY,
    lease_id INTEGER NOT NULL REFERENCES leases(id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    amount DOUBLE PRECISION NOT NULL,
    payment_date DATE NOT NULL,
    payment_method VARCHAR(100) NOT NULL
);
```

## 5. Penerapan Pilar OOP
### - Inheritance
File `Property.java`
```java
public abstract class Property {
    private final int id;
    private final String address;
    private final double rentPrice;

    public Property(int id, String address, double rentPrice) { ... }

    /** Setiap jenis properti wajib menentukan cara menghitung pajaknya */
    public abstract double hitungPajak();
}

public class House extends Property {
    public House(int id, String address, double rentPrice) { super(id, address, rentPrice); }

    @Override
    public double hitungPajak() {
        return getRentPrice() * 0.05;   // pajak 5 % khusus rumah
    }
}

public class Apartment extends Property {
    public Apartment(int id, String address, double rentPrice) { super(id, address, rentPrice); }

    @Override
    public double hitungPajak() {
        return getRentPrice() * 0.08;   // pajak 8 % khusus apartemen
    }
}
```

Pada lapisan model, konsep inheritance diterapkan dengan mendefinisikan satu kelas induk bernama `Property`. Kelas ini memuat atribut dan perilaku umum yang dimiliki semua jenis properti, misalnya `id`, `alamat`, `hargaSewa`, serta operasi dasar seperti double `hitungPajak()`.

Dua kelas turunan, `House` dan `Apartment`, mewarisi seluruh properti serta metode dari `Property`, lalu melakukan penyesuaian (*override*) pada perilaku tertentu, terutama cara menghitung pajak.

### - Encapsulation
File `Property.java`
```java
public class Property {
    private final int id;
    private final String address;
    private final String description;
    private final String name;
    private final double rentPrice;
    private final String status;
    private final String type;

    // data di-injeksi lewat konstruktor
    public Property(int id, String address, String description,
                    String name, double rentPrice, String status, String type) {
        this.id = id;
        this.address = address;
        this.description = description;
        this.name = name;
        this.rentPrice = rentPrice;
        this.status = status;
        this.type = type;
    }

    // hanya menyediakan pembaca (getter),
    // tidak ada setter agar objek bersifat immutable
    public int getId()          { return id; }
    public String getAddress()  { return address; }
    public String getName()     { return name; }
    public double getRentPrice(){ return rentPrice; }
    public String getStatus()   { return status; }
    public String getType()     { return type; }
}
```

Pada lapisan model seluruh data sensitif ditempatkan di dalam atribut **private**, lalu diekspos secukupnya melalui **getter**. Dengan cara ini objek menjadi lebih terisolasi dari kode luar, sehingga perubahan nilai hanya terjadi lewat jalur resmi yang lebih mudah diawasi.

Prinsip yang sama dipakai di `Tenant`, `User`, dan `Lease`. Semua kolom mereka bersifat **private**, kode di luar kelas tidak dapat mengubah nilai sembarangan, tetapi harus lewat konstruktor atau setter terkontrol (bila memang disediakan).

### - Polymorphism
Semua layar (`LoginScreen`, `HomeScreen`, `AddPropertyScreen`, dll.) adalah turunan **HBox** atau **VBox**, yang sebenarnya mewarisi **Parent**.
```java
public class LoginScreen extends VBox { … }
public class HomeScreen  extends HBox { … }
```

Metode `Navigator.navigate(Parent screen)` cukup menerima tipe umum Parent.
Saat dipanggil:
```java
Navigator.navigate(new LoginScreen());
Navigator.navigate(new HomeScreen());
```


### - Abstract
```java
public abstract class Property {
    private final int id;
    private final String address;
    private final double rentPrice;

    // konstruktor & getter …

    /** Setiap jenis properti harus menentukan cara menghitung pajaknya sendiri */
    public abstract double hitungPajak();
}
```
```java
public class House extends Property {
    @Override
    public double hitungPajak() { return getRentPrice() * 0.05; }
}

public class Apartment extends Property {
    @Override
    public double hitungPajak() { return getRentPrice() * 0.08; }
}
```

`Property` bertindak sebagai *blueprint* abstrak yang menyimpan data dasar dan mendefinisikan metode `hitungPajak()` tanpa isi. Sementara `House` dan `Apartment` mengisi rumus pajak masing-masing. Alhasil, detail perhitungan diserahkan ke *subclass*, tetapi semua objek tetap bisa dipakai seragam lewat tipe **Property**.

## 6. Demo Proyek
- Google Drive: https://drive.google.com/drive/folders/1PkpknbogjfIE0ddXj1pHdcgtdhrtggO1
