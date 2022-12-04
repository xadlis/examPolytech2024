# Formation-java-basics

## Default launch

By default, application will start with h2 and no TLS.

```bash
mvn spring-boot:run
```

## DB management

* To launch application with MySQL :

```bash
mvn spring-boot:run -Dspring.profiles.active=sql
```

* To launch application with h2 (this is already the default profile) :

```bash
mvn spring-boot:run -Dspring.profiles.active=h2
```

## TLS Configuration

### Configuration

* Modify /etc/hosts to add :

```bash
127.0.0.1 backend.formations.lu
```

* Generate PKCS12 file for TLS :

```bash
$> keytool -genkeypair -alias backend.formations.lu -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore backend.formations.lu.p12 -validity 3650
```

* File information with CN=backend.formations.lu

* Copy generated file in src/main/resources

### Usage

To launch application with TLS and h2 or sql (database need to be declared in this mode):

```bash
mvn spring-boot:run -Dspring.profiles.active=sql,ssl
or
mvn spring-boot:run -Dspring.profiles.active=h2,ssl
```