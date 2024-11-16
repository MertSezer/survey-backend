# Survey Backend

Survey Backend, anket oluşturma, yönetme ve etkileşim sağlama amacıyla geliştirilmiş bir RESTful API'dir. Kullanıcılar anket yaratabilir, ankete oy verebilir, yorum yapabilir ve hashtag'ler aracılığıyla ilgili anketlere erişebilir. Bu uygulama, modern web uygulamaları için temel backend işlevselliğini sağlar.

# Swagger Documentation URL:
http://localhost:8091/swagger-ui/index.html

## Özellikler

- **Hashtag Yönetimi:**
  - Hashtag oluşturma, güncelleme, silme ve listeleme.
  - Anketlerin hashtag'leri ile ilişkilendirilmesi, kullanıcıların ilgili anketlere ulaşmasını sağlar.

- **Anket Yönetimi:**
  - Anket oluşturma, güncelleme, silme ve listeleme.
  - Kullanıcıların anketlere oy vermesi.
  - Yorum yapılabilmesi için yorum ekleme, güncelleme ve silme.

- **Kullanıcı Yönetimi:**
  - Kullanıcı kaydı (Sign-Up), giriş (Login) ve oturum açma.

- **Yorum Yönetimi:**
  - Yorum oluşturma, silme, güncelleme ve listeleme.

## Teknolojiler

Bu proje aşağıdaki teknolojilerle geliştirilmiştir:

- **Java 11+**: Uygulamanın temel dili.
- **Spring Boot**: Java uygulaması için kullanılan popüler framework.
- **Spring Data JPA**: Veritabanı erişimi için kullanılır.
- **H2 / PostgreSQL**: Veritabanı (H2 yerel geliştirme için veya PostgreSQL kullanılabilir).
- **Spring Security**: Kullanıcı kimlik doğrulama ve yetkilendirme için.
- **JWT (JSON Web Tokens)**: Kullanıcı kimlik doğrulama ve yetkilendirme.
- **Maven**: Proje bağımlılıkları ve yapı yönetimi.

## API Sonuçları

### 1. **Kullanıcı Yönetimi**

- **POST /users/signup**  
  Kullanıcı kaydı oluşturur.

  **Body**:  
  ```json
  {
    "username": "string",
    "email": "string",
    "password": "string"
  }
  ```

- **POST /users/login**  
  Kullanıcı girişini yapar ve JWT token döner.

  **Body**:  
  ```json
  {
    "email": "string",
    "password": "string"
  }
  ```

### 2. **Hashtag Yönetimi**

- **POST /hashtags**  
  Yeni bir hashtag oluşturur.

  **Body**:  
  ```json
  {
    "name": "string"
  }
  ```

- **GET /hashtags**  
  Tüm hashtag'leri listeleyin.

- **PUT /hashtags/{id}**  
  Belirli bir hashtag'i günceller.

  **Body**:  
  ```json
  {
    "name": "string"
  }
  ```

- **DELETE /hashtags/{id}**  
  Belirli bir hashtag'i siler.

### 3. **Anket Yönetimi**

- **POST /surveys**  
  Yeni bir anket oluşturur.

  **Body**:  
  ```json
  {
    "title": "string",
    "description": "string",
    "hashtags": ["hashtag1", "hashtag2"]
  }
  ```

- **GET /surveys**  
  Tüm anketleri listeler.

- **GET /surveys/{id}**  
  Belirli bir anketi getirir.

- **PUT /surveys/{id}**  
  Belirli bir anketi günceller.

  **Body**:  
  ```json
  {
    "title": "string",
    "description": "string",
    "hashtags": ["hashtag1", "hashtag2"]
  }
  ```

- **DELETE /surveys/{id}**  
  Belirli bir anketi siler.

### 4. **Yorum Yönetimi**

- **POST /comments**  
  Yeni bir yorum oluşturur.

  **Body**:  
  ```json
  {
    "surveyId": "survey_id",
    "comment": "string"
  }
  ```

- **GET /comments**  
  Tüm yorumları listeler.

- **PUT /comments/{id}**  
  Belirli bir yorumu günceller.

  **Body**:  
  ```json
  {
    "comment": "string"
  }
  ```

- **DELETE /comments/{id}**  
  Belirli bir yorumu siler.

## Kurulum

Bu proje, Java ve Spring Boot kullanılarak geliştirilmiştir. Aşağıdaki adımları takip ederek projeyi kurup çalıştırabilirsiniz.

### 1. Depoyu Klonlayın
Öncelikle bu repository'i kendi bilgisayarınıza klonlayın:

```bash
git clone https://github.com/MertSezer/survey-backend.git
cd survey-backend
```

### 2. Bağımlılıkları Yükleyin

Proje Maven kullanılarak yapılandırıldığından, bağımlılıkları yüklemek için aşağıdaki komutu çalıştırabilirsiniz:

```bash
mvn install
```

Bu komut, gerekli tüm bağımlılıkları ve yapılandırmayı yükleyecektir.

### 3. Uygulama Yapılandırması

Uygulama yapılandırmalarını `application.properties` veya `application.yml` dosyasında bulabilirsiniz. Bu dosyayı açarak veritabanı bağlantısı ve diğer ayarları yapılandırabilirsiniz.

**Örnek `application.properties` dosyası:**
```properties
# H2 Database (Yerel geliştirme için)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# PostgreSQL (Canlı ortam için)
# spring.datasource.url=jdbc:postgresql://localhost:5432/polla
# spring.datasource.username=your_db_username
# spring.datasource.password=your_db_password

# JWT Secret Key
jwt.secret=your_secret_key

# Server port
server.port=8091
```

- **Veritabanı**: H2 veritabanı yerel geliştirme için kullanılır. PostgreSQL gibi bir veritabanı kullanmak isterseniz, ilgili bağlantı ayarlarını `application.properties` içinde değiştirebilirsiniz.
- **JWT Secret Key**: JWT token'ları için gizli anahtarınızı burada belirleyin.

### 4. Uygulamayı Başlatın

Spring Boot uygulamanızı başlatmak için aşağıdaki komutu kullanabilirsiniz:

```bash
mvn spring-boot:run
```

Uygulama başlatıldığında, API'ye `http://localhost:8080` adresinden erişebilirsiniz.

### 5. Postman veya CURL ile API Testi

Uygulamanın düzgün çalıştığından emin olmak için Postman veya CURL gibi araçlarla API'leri test edebilirsiniz.

---

## Katkı Sağlama

Bu projeye katkı sağlamak isterseniz, lütfen aşağıdaki adımları izleyin:

1. Bu projeyi fork'layın.
2. Değişikliklerinizi yeni bir branch'te yapın (`git checkout -b feature-xyz`).
3. Değişikliklerinizi commit edin ve push'layın (`git push origin feature-xyz`).
4. Pull request gönderin.

## Lisans

Bu proje [MIT Lisansı](LICENSE) altında lisanslanmıştır.
