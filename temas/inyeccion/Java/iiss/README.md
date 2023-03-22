## Compilación y Ejecución
Descargar todo el contenido de las carpetas y ejecutar en el directorio raíz del proyecto:
>- Windows: .\gradlew.bat bootRun
>- MacOS/Linux: ./gradlew bootRun

Para hacer un proyecto propio hay que descargar la configuración inicial desde [la página oficial de spring](https://start.spring.io/) y, a partir de ahí, modificarlo. Para este proyecto lo he descargado usando gradle, la versión de spring 3.0.4 para Java 17, ninguna dependencia adicional y la configurción de carpetas que podeis ver en el repositorio.

## Explicación

### BankAccount
El código proporcionado se divide en los archivos *BankAccount.java*, *BankAccountComparatorByCreationDate.java* y *BankAccountComparatorById.java* dónde el único que ha sido levemente modificado es *BankAccount.java* que, para mostrar la inyección tanto por construcción como a través de propiedades, se ha añadido un constructor que recibe todos los atributos por parámetros y se ha modificado el dado para que sólo inicialice el ID. 

### Package en java
Los paquetes en java no son sino un mecanismo de encapsulación de grupos de clases, subpaquetes e interfaces. en este caso lo utilizaremos para que sea más sencillo su localización y uso.

>En cada archivo se debe especificar el paquete al que pertenece, en este caso todos pertenecen a *com.iiss*.

### springContext
En éste archivo XML es dónde tenemos configuradas las distintas inyecciones mediante **beans** utilizando *springframework*. Debe ser guardado en la carpeta resources, tal y cómo vemos en el repositorio. Inyectaremos dos métodos estáticos de la clase *LocalDate*, dos objetos comparadores y tres objetos de la clase *BankAccount*.

#### Inyección de métodos estáticos
Siguen la siguiente estructura:
```xml
    <bean id="nombreBean"
        class="nombre.de.la.clase"
        factory-method="Nombre del metodo">
        <constructor-arg name="parametro1" value="valor" />
        <!-- ... -->
    </bean> 
```

>Será referenciado como si fuera un objeto más de los definidos en este fichero.

Todas las clases serán escritas junto al paquete al que pertenecen. En este caso necesitamos este tipo de inyección para poder definir los parámetros y propiedades de tipo LocalDate de los constructores de BankAccount, ya que éste tipo no consta de constructor.

#### Inyeción mediante constructor
Tienen una estructura aún más simple que la anterior:

```xml
     <bean id="nombreBean" class="nombre.de.la.clase">
        <constructor-arg name="Parametro1" value="valor">
        </constructor-arg>
        <constructor-arg name="Parametro2" ref="objetoYaCreado">
        </constructor-arg>
        <!-- ... -->
    </bean>
```
> Los objetos que definamos como *Bean* y los necesitemos como parámetro o propiedad para otro  *Bean*, se cambiará el campo *value* por *ref* cuyo valor es el ID que hayamos definido al objeto (o resultado que devuelva un método estático).

#### Inyección mediante propiedades
También pueden tener parámetros del constructor:

```xml
     <bean id="nombreBean" class="nombre.de.la.clase">

        <property name="Atributo1" ref="objetoYaCreado">
        </property>
        <!-- ... -->

    </bean>
```

>El campo *property* se encarga de dar valor a los distintos atributos de la clase.


### Pruebas
Los tests se dan en un fichero a parte, *BankAccountApplication.java*. En su main primero cargamos el fichero xml antes explicado y, a partir de ahí, inyectamos 3 objetos BankAccount, que son los tres definidos en *springContext*. Los dos primeros son inyectados mediante constructor, tienen id y fechas distintas así como el primero tiene definido el comparador mediante ID. El tercero se inyecta mediante propiedades, haciendo uso del constructor cuyo único parámetro es el ID y definiendo el resto de atributos mediante propiedades, su comparador es mediante fechas. Las siguientes comparaciones son para comprobar que los objetos están bien creados.

### Conclusión
Gracias a estas inyecciones, la clase *BankAccountApplication* sólo tiene una dependencia con *BankAccount* y no de las clases *LocalDate* y los comparadores.
