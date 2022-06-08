# Food My Project

### Install
1. Soyez sur d'avoir Android Studio, PHP et MYSQL d'installer sur votre machine.
2. Cloner l'api sur votre machine local . Configurer votre port et votre connection BDD.
3. Cloner ce répertoire sur votre machine local. Ouvrez Android Studio et ouvrez ce projet . Configurer le (voir configuration)
4. Tout est bon. Plus qu'a vous lancer ;)

### CONFIGURATION
Dans le projet, dans java/com/nfs/foodmyproject/config il y a un fichier ApiEndpoint.java qui contient des variables pour communiqué avec l'API distante. Il faut configurer l'endpoint de celle-ci sur :

```java
    public static String BASE = "http://192.168.1.16:8000/api/";
```

Le port par défaut de l'API Symfony est 8000. Pour connaitre son adresse ip, dans votre terminal préféré il faut utiliser la commande :
```bash
ipconfig

Carte réseau sans fil Wi-Fi :

   Suffixe DNS propre à la connexion. . . : home
   Adresse IPv6. . . . . . . . . . . . . .: 2a01:cb06:14f:b00:68c5:1ab9:f7b2:40cb
   Adresse IPv6 temporaire . . . . . . . .: 2a01:cb06:14f:b00:84b9:1e1a:2a47:2581
   Adresse IPv6 de liaison locale. . . . .: fe80::68c5:1ab9:f7b2:40cb%8
   Adresse IPv4. . . . . . . . . . . . . .: 192.168.1.16
   Masque de sous-réseau. . . . . . . . . : 255.255.255.0
   Passerelle par défaut. . . . . . . . . : fe80::7ec1:77ff:fe0f:8d90%8
                                       192.168.1.1
```

Ici j'ai récupéré mon adresse IPV4

### API
[Lien  du dépot de l'API](https://github.com/Druxys/symfony-api-android)

## Créer un APK :
Dans Android Studio -> Build -> Build Bundle(s) APK -> Build APK(s)

Un fichier sera généré automatiquement.
