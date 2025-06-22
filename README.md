# App de Registro y consulta de películas

Aplicación móvil desarrollada en Kotlin* usando Jetpack Compose, que permite registrar, editar, eliminar y consultar películas
localmente con Room y de forma remota mediante una API externa (OMDb). Es una solución completa que integra almacenamiento local, llamadas a servicios REST y arquitectura MVVM.

1. Funcionalidades

-  Registro de películas con título, año, director y URL del póster.
-  Edición y eliminación de registros existentes.
-  Consulta en tiempo real desde la **API pública OMDb**.
-  Almacenamiento local con **Room Database**.
-  Navegación entre pantallas con **Navigation Compose**.
-  Arquitectura basada en **MVVM** (Model-View-ViewModel).
-  Manejo de estados con StateFlow y mutableStateOf

2. Estructura del proyecto

- UI: Jetpack Compose
- Base de datos local: Room
- Red / API: Retrofit
- Gestión de estado* ViewModel + Coroutines + StateFlow
- Navegación: Navigation Compose
- Diseño reactivo: Material3

3. Librerías utilizadas

 Jetpack / AndroidX
- androidx.core:core-ktx
- androidx.lifecycle:lifecycle-runtime-ktx
- androidx.lifecycle:lifecycle-livedata-ktx
- androidx.navigation:navigation-compose
- androidx.activity:activity-compose
- androidx.compose.* (UI, material3, tooling, runtime-livedata)

 Room (Base de datos local)
- androidx.room:room-runtime
- androidx.room:room-ktx
- androidx.room:room-compiler

Retrofit (API REST)
- com.squareup.retrofit2:retrofit
- com.squareup.retrofit2:converter-gson

Corrutinas
- org.jetbrains.kotlinx:kotlinx-coroutines-core
- org.jetbrains.kotlinx:kotlinx-coroutines-android

Testing
- junit:junit
- androidx.test.ext:junit
- androidx.test.espresso:espresso-core
- androidx.compose.ui:ui-test-junit4

4. Versiones principales

 Android Gradle Plugin (AGP): 8.1.0  
 Kotlin: 2.0.21   
 Room:2.7.1         
 Compose BOM: 2024.09.00    
 Retrofit: 2.9.0         
 Coroutines: 1.10.2        
 Navigation: 2.9.0         

5. Cómo ejecutar el proyecto

1. Clona el repositorio.
2. Abre el proyecto en Android Studio.
3. Asegúrate de tener un emulador o dispositivo físico configurado.
4. Haz clic en Run dentro del main activity
