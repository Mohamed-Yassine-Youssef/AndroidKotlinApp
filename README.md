# 📚 Catalogue de Livres - Android Kotlin Jetpack Compose

## 🎯 Aperçu du Projet

Application Android native développée en **Kotlin** avec **Jetpack Compose** et l'architecture **MVVM**. Elle permet de parcourir un catalogue de livres, voir les détails, gérer ses favoris avec une navigation intuitive et un écran d'accueil.

## 🏗️ Structure du Projet

```
📁 com.example.bookcatalog/
├── 📁 data/
│   ├── repository/
│   │   └── BookRepository.kt
│   └── source/
│       └── FakeBookData.kt
├── 📁 domain/
│   └── model/
│       └── Book.kt
├── 📁 presentation/
│   ├── components/
│   │   ├── BookItem.kt
│   │   ├── SearchAndFilterSection.kt
│   │   └── BottomNavBar.kt
│   ├── screens/
│   │   ├── WelcomeScreen.kt
│   │   ├── BookListScreen.kt
│   │   ├── BookDetailScreen.kt
│   │   └── FavoriteBooksScreen.kt
│   └── viewmodel/
│       └── BookViewModel.kt
├── 📁 ui/theme/
│   ├── Theme.kt
│   └── Type.kt
└── MainActivity.kt
```

### **Model** - Couche de Données
```
📁 domain/model/
  └── Book.kt - Data class représentant un livre
📁 data/
  ├── repository/
  │   └── BookRepository.kt - Gestion des données avec Flow
  └── source/
      └── FakeBookData.kt - Données statiques simulées
```

### **View** - Interface Utilisateur
```
📁 presentation/
  ├── components/
  │   ├── BookItem.kt - Composable réutilisable pour un livre
  │   ├── SearchAndFilterSection.kt - Barre de recherche et filtres
  │   └── BottomNavBar.kt - Navigation entre les écrans principaux
  ├── screens/
  │   ├── WelcomeScreen.kt - Écran d'accueil avec navigation
  │   ├── BookListScreen.kt - Liste des livres avec recherche
  │   ├── BookDetailScreen.kt - Détails complets d'un livre
  │   └── FavoriteBooksScreen.kt - Gestion des livres favoris
  └── viewmodel/
      └── BookViewModel.kt - Gestion d'état avec StateFlow
```

## 🚀 Technologies Utilisées

- **Kotlin** - Langage principal
- **Jetpack Compose** - UI moderne et déclarative
- **Material Design 3** - Design system avec thème adaptatif
- **Navigation Compose** - Navigation entre écrans
- **StateFlow/Flow** - Gestion d'état réactive
- **Dynamic Color** - Thème système adaptatif (mode sombre/clair)

## 📱 Fonctionnalités

### Écran d'Accueil (Welcome)
- ✅ **Interface d'accueil** attractive et moderne
- ✅ **Bouton de navigation** vers le catalogue des livres
- ✅ **Présentation** de l'application

### Écran Principal (Catalogue)
- ✅ **Affichage en carte** avec couverture, titre, auteur
- ✅ **Recherche en temps réel** (titre, auteur, genre)
- ✅ **Filtre favoris** avec toggle switch
- ✅ **Compteur dynamique** de livres affichés
- ✅ **Gestion favoris** avec icône cœur
- ✅ **Note avec étoiles** et année de publication
- ✅ **Navigation bottom bar** vers les favoris

### Écran Favoris
- ✅ **Liste dédiée** aux livres favoris
- ✅ **Recherche dans favoris** avec filtre temps réel
- ✅ **Gestion des favoris** (ajout/suppression)
- ✅ **Interface cohérente** avec le catalogue principal
- ✅ **Navigation fluide** via bottom bar

### Écran Détails
- ✅ **Navigation fluide** avec bouton retour
- ✅ **Informations complètes** (description longue)
- ✅ **Image agrandie** de la couverture
- ✅ **Chip genre** avec Material Design
- ✅ **Tableau d'informations** organisé
- ✅ **Action favori** dans la top bar
- ✅ **Bouton d'achat Amazon** avec redirection vers le produit

### Navigation
- ✅ **Bottom Navigation Bar** moderne et intuitive
- ✅ **Navigation entre écrans** (Catalogue ↔ Favoris)
- ✅ **États actifs** visuellement distincts
- ✅ **Icônes Material** expressives

## 🎨 Fonctionnalités de Thème

### **Mode Sombre Adaptatif** 🌙
- **Détection automatique** des préférences système
- **Basculement instantané** quand l'utilisateur change le thème de son mobile
- **Cohérence visuelle** sur tous les écrans (Welcome, Catalogue, Favoris, Détails)
- **Material Design 3** avec couleurs optimisées pour chaque mode
- **Lisibilité parfaite** en mode clair et sombre
- **Économie d'énergie** sur écrans OLED avec le mode sombre

### **Implémentation**
```kotlin
// Dans MainActivity et Theme.kt
@Composable
fun BookCatalogTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Détection automatique
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        dynamicDarkColorScheme(LocalContext.current)
    } else {
        dynamicLightColorScheme(LocalContext.current)
    }
    // ...
}
```

## 🎨 Choix de Design
- Couleurs dynamiques et thème adaptatif
- **Mode sombre automatique** basé sur les préférences système
- **Thème cohérent** sur tous les écrans
- Cards avec élévation subtile
- Typography cohérente
- États de hover et focus
- Bottom Navigation moderne

### **UX/UI Moderne**
- Interface intuitive et responsive
- **Adaptation automatique** au thème système (clair/sombre)
- **Expérience visuelle cohérente** quel que soit le mode
- Animations fluides (implicite Compose)
- États de chargement avec CircularProgressIndicator
- Gestion d'erreurs avec messages utilisateur
- Navigation contextuelle et cohérente

## 🔧 Bonnes Pratiques Implémentées

### **Architecture**
- ✅ **Séparation des responsabilités** (Model-View-ViewModel)
- ✅ **Injection de dépendances** simplifiée
- ✅ **Flow pour la réactivité** (StateFlow, MutableStateFlow)
- ✅ **Repository pattern** pour l'abstraction des données
- ✅ **Source de données centralisée** (FakeBookData)

### **Compose**
- ✅ **Composables réutilisables** (`BookItem`, `SearchAndFilterSection`, `BottomNavBar`)
- ✅ **Architecture modulaire** avec dossier `components/`
- ✅ **State hoisting** avec `remember` et `collectAsState`
- ✅ **LaunchedEffect** pour les side effects
- ✅ **Navigation type-safe** avec arguments
- ✅ **Navigation Compose** avec gestion d'état

### **Kotlin**
- ✅ **Data classes** immutables
- ✅ **Extension functions** pour la lisibilité
- ✅ **Null safety** avec types optionnels
- ✅ **Coroutines** pour l'asynchrone
- ✅ **Organisation modulaire** des données

## 📊 Gestion d'État

```kotlin
data class BookUiState(
    val books: List<Book> = emptyList(),
    val filteredBooks: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val showFavoritesOnly: Boolean = false,
    val searchQuery: String = ""
)
```

**Single Source of Truth** avec `StateFlow` pour maintenir la cohérence d'état entre tous les écrans, la navigation et les actions externes (comme l'achat).

## 🔄 Flux de Données

```
FakeBookData → Repository → ViewModel → Compose State → UI Update
     ↑                                                    ↓
Navigation ← StateFlow ← User Actions ← UI Interactions ←
```

## 🗂️ Organisation des Données

### **FakeBookData.kt**
- **Données centralisées** : Tous les livres dans un seul fichier
- **Structure cohérente** : Format uniforme pour tous les livres
- **Facilité de maintenance** : Ajout/modification simple des livres
- **Performance** : Chargement instantané des données

## 🎯 Pourquoi ces Choix ?

### **MVVM + Repository + Data Source**
- **Testabilité** : Logique séparée de l'UI
- **Maintenabilité** : Code organisé et modulaire
- **Réactivité** : Mises à jour automatiques avec Flow
- **Extensibilité** : Facile d'ajouter de vraies sources de données

### **Navigation Compose + Bottom Bar**
- **UX moderne** : Pattern familier aux utilisateurs
- **Performance** : Navigation efficace sans reconstruction
- **Flexibilité** : Ajout facile de nouveaux écrans

### **Jetpack Compose**
- **Performance** : Recomposition intelligente
- **Productivité** : Moins de code boilerplate
- **Moderne** : Paradigme déclaratif
- **Thème adaptatif** : Support natif du mode sombre système

### **Données Statiques Centralisées**
- **Simplicité** : Focus sur l'architecture sans complexité DB
- **Performance** : Accès instantané aux données
- **Démo** : Idéal pour présenter les concepts
- **Organisation** : Structure claire et maintenant

## 🚀 Installation

1. **Cloner** le projet dans Android Studio
2. **Synchroniser** Gradle
3. **Lancer** sur émulateur ou device Android

### Dépendances Gradle
```kotlin
// Compose BOM
implementation platform('androidx.compose:compose-bom:2024.02.00')
implementation 'androidx.compose.ui:ui'
implementation 'androidx.compose.material3:material3'

// Navigation
implementation 'androidx.navigation:navigation-compose:2.7.6'

// ViewModel
implementation 'androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0'

// Coil pour images
implementation 'io.coil-kt:coil-compose:2.5.0'
```

## 📱 Écrans de l'Application

### **WelcomeScreen** 🏠
- Point d'entrée de l'application
- Interface d'accueil accueillante
- Navigation vers le catalogue principal

### **BookListScreen** 📖
- Catalogue complet des livres
- Recherche et filtrage avancés
- Gestion des favoris en temps réel

### **FavoriteBooksScreen** ❤️
- Collection personnelle de favoris
- Recherche dédiée dans les favoris
- Interface cohérente avec le catalogue

### **BookDetailScreen** 📄
- Informations détaillées sur chaque livre
- Image haute qualité
- Actions de favoris intégrées
- Bouton d'achat direct vers Amazon

## 📈 Évolutions Possibles

- 🔄 **Base de données** Room pour persistance
- 🌐 **API REST** avec Retrofit
- 🔍 **Recherche avancée** avec filtres multiples
- 🎨 **Thème personnalisé** avec choix manuel des couleurs
- 🏷️ **Catégories** et tags
- 📊 **Statistiques** de lecture
- 💫 **Animations** personnalisées
- 🔔 **Notifications** de nouveaux livres
- 👤 **Profil utilisateur** et préférences
- 📈 **Recommandations** basées sur les favoris
- 🛒 **Intégration e-commerce** avec d'autres plateformes
- 💳 **Système de wishlist** et suivi des prix

## 🎨 Captures d'Écran

*[Ajoutez ici vos captures d'écran de l'application]*

---

**Développé avec ❤️ en Kotlin & Jetpack Compose**

*Une application démontrant les meilleures pratiques Android modernes avec une architecture MVVM complète et une navigation intuitive.*
