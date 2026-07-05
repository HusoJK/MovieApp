# MovieApp

Kotlin ve Jetpack Compose kullanılarak geliştirilmiş, **Clean Architecture** prensiplerine uygun bir film keşif uygulaması. Kullanıcılar film arayabilir, film listesini görüntüleyebilir ve seçtikleri filmin detaylarını inceleyebilir.

Bu proje, Jetpack Compose ile Android Uygulama Geliştirme eğitimi kapsamında geliştirilmiştir.

---

## 📱 Özellikler

- Film arama ve listeleme
- Film detay sayfası
- API üzerinden gerçek zamanlı veri çekme
- Loading / Success / Error durumlarının yönetimi

---

## Mimari

Proje, **Clean Architecture** yaklaşımıyla 3 katmana ayrılmıştır:

```
├── data/            → Dış dünya ile iletişim (API, DTO'lar, Repository implementasyonu)
├── domain/          → İş mantığı (Model, Repository interface, Use Case'ler)
├── presentation/    → UI (Compose Screen'ler, ViewModel, State/Event)
└── util/            → Ortak yardımcı sınıflar (Resource, Constants)
```

### Data Layer
- `remote/dto` → API'den gelen ham veriyi karşılayan DTO sınıfları (`MoviesDto`, `MovieDetailDto`, `Rating`, `Search`)
- `remote/MovieAPI.kt` → Retrofit servis arayüzü
- `repository/MovieRepositoryImpl.kt` → Domain katmanındaki repository'nin gerçek implementasyonu
- `di/AppModule.kt` → Hilt ile bağımlılıkların (Retrofit, Repository vb.) sağlanması

### Domain Layer
- `model` → Saf Kotlin veri modelleri (`Movie`, `MovieDetail`) — UI ve API'den bağımsız
- `repository/MovieRepository.kt` → Repository interface (soyutlama)
- `useCase` → Tek bir işi yapan iş mantığı sınıfları (`GetMoviesUseCase`, `GetMovieDetailsUseCase`)

### Presentation Layer
- `movies` → Film listesi ekranı (`MoviesScreen`, `MoviesViewModel`, `MoviesState`, `MoviesEvent`, `MovieListRow`)
- `movieDetail` → Film detay ekranı (`MovieDetailScreen`, `MovieDetailViewModel`, `MovieDetailState`)
- `ui/theme` → Compose tema dosyaları (Color, Type, Theme)
- `Screen.kt` → Navigation route tanımları
- `MainActivity.kt` → Uygulamanın giriş noktası

---

## Kullanılan Teknolojiler

| Teknoloji | Kullanım Amacı |
|---|---|
| **Kotlin** | Ana programlama dili |
| **Jetpack Compose** | Deklaratif UI oluşturma |
| **Clean Architecture** | Katmanlı, test edilebilir, sürdürülebilir kod yapısı |
| **MVVM** | Presentation katmanı mimarisi |
| **Hilt** | Dependency Injection |
| **Retrofit** | REST API istekleri |
| **Coroutines** | Asenkron işlemler |
| **Kotlin Flow** | Reaktif veri akışı (Repository → Use Case → ViewModel → UI) |
| **Use Case Pattern** | İş mantığının UI ve veriden izole edilmesi |
| **State/Event Pattern** | UI durumlarının ve kullanıcı etkileşimlerinin yönetimi |

---

## Bu Projede Öğrendiklerim

- **Clean Architecture katmanlarını ayırmak**: Data, Domain ve Presentation katmanlarını birbirinden bağımsız hale getirerek her katmanın tek bir sorumluluğu olmasını sağladım. Bu sayede API veya UI değişse bile iş mantığı etkilenmiyor.
- **DTO ↔ Domain Model dönüşümü**: API'den gelen ham veriyi (`Dto`) doğrudan UI'da kullanmak yerine, `domain/model` katmanındaki temiz modellere (`Movie`, `MovieDetail`) mapleyerek katmanlar arası bağımlılığı azalttım.
- **Use Case mantığı**: Her use case'in (`GetMoviesUseCase`, `GetMovieDetailsUseCase`) tek bir işi yaptığını, ViewModel'in doğrudan Repository ile değil Use Case'ler üzerinden konuştuğunu öğrendim.
- **Repository Pattern**: `domain/repository` içindeki interface ile `data/repository` içindeki implementasyonu ayırarak bağımlılıkların soyutlama üzerinden yönetilmesini (Dependency Inversion) uyguladım.
- **Kotlin Flow ile reaktif veri akışı**: Repository'den gelen verinin `Flow` olarak sarılıp Use Case üzerinden ViewModel'e, oradan da Compose UI'a nasıl aktığını öğrendim. `collect` / `collectAsState` gibi mekanizmalarla UI'ın veri değişikliklerine otomatik olarak nasıl tepki verdiğini kavradım. Bu sayede tek seferlik (one-shot) API çağrıları yerine, veri kaynağına bağlı sürekli güncellenebilen bir yapı kurmayı öğrendim.
- **Hilt ile Dependency Injection**: `AppModule` üzerinden Retrofit instance'ı ve Repository'nin nasıl sağlandığını, `@HiltAndroidApp` ve `@AndroidEntryPoint` annotation'larının nasıl çalıştığını öğrendim.
- **Resource wrapper pattern**: `util/Resource.kt` ile API çağrılarının Loading / Success / Error durumlarını tek bir sealed class üzerinden yönetmeyi öğrendim. Bu, UI tarafında duruma göre farklı ekranlar (yükleniyor animasyonu, hata mesajı, veri) göstermeyi kolaylaştırdı.
- **State & Event Pattern (MVI'ya yakın MVVM)**: Her ekran için ayrı `State` (ekranın o anki durumu) ve `Event` (kullanıcı etkileşimleri, örn. arama yapma) sınıfları tanımlayarak tek yönlü veri akışı (unidirectional data flow) mantığını kavradım.
- **Jetpack Compose ile UI geliştirme**: XML yerine tamamen Compose ile ekran (`Screen`) ve liste elemanı (`Row`) bileşenleri oluşturmayı, state'e göre yeniden çizimin (recomposition) nasıl tetiklendiğini öğrendim.
- **Navigation**: `Screen.kt` üzerinden route'ları merkezi bir yerden yönetmeyi öğrendim.
- **Retrofit ile network katmanı kurmak**: API arayüzü tanımlama, DTO modelleme ve gelen cevabı Repository katmanında işleme adımlarını uyguladım.

---

## Ekran Görüntüleri

<p align="center">
  <img width="250" alt="Film Listesi" src="https://github.com/user-attachments/assets/ed371617-d418-4be3-80b0-785bce478bc2" />
  <img width="250" alt="Film Detayı" src="https://github.com/user-attachments/assets/cc4777e8-daa6-43b8-a02f-b59bfe7f0cb3" />
  <img width="250" alt="Film Arama" src="https://github.com/user-attachments/assets/1dc57ab5-e53a-4aa3-a580-7e38d2855d36" />
</p>

