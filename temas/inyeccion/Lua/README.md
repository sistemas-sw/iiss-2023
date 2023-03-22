# Inyección de dependencias en Lua

Para Lua hay varias bibliotecas para implementar la inyección de dependencias en el lenguaje, en este breve docuemento teniendo en mente el trabajo de otra asignatura utilizaremos una biblioteca muy útil para la realización de test unitarios en Lua, estamos hablando de la biblioteca **lunatest** que con ayuda de patrón de **Setter Injection** vamos a ver un ejemplo muy claro en el que se usa la inyección de dependencias. Como la práctica de la que hemos hablado está relacionada con motor de coches/caravanas, el ejemplo de esta práctica será la clase **Motor**. Para esta clase tendremos operaciones simples como *arrancar*, *apagar*, *aumentarMarcha*, *disminuirMarcha*, *puntoMuerto*.

```Lua
    local Motor = {}
    
    function Motor:new()
        local o = {}
        self.__index = self
        setmetatable(o, self)
        o.arrancar = function() self.turnOn = 1 self.marchas = 0 end
        o.apagar = function() self.turnOn = 0    end
        o.aumentarMarcha = function() self.marchas = self.marchas + 1  end
        o.disminuirMarcha = function() self.marchas = self.marchas - 1 end
        o.puntoMuerto = function() self.marchas = -1    end
        o.encendido = function() return self.turnOn end
        o.marchasMotor = function() return self.marchas end
        return o
    end

    --Utilizando las funciones set... permitimos que los usuarios puedan especificar sus propias implementacionespara cada operación definida en la clase
    function Motor:setArrancar(arrancarFunc)
        self.arrancar = arrancarFunc
    end

    function Motor:setApagar(apagarFunc)
        self.apagar = apagarFunc
    end

    function Motor:setAumentarMarcha(aumentarMarchaFunc)
        self.aumentarMarcha = aumentarMarchaFunc
    end

    function Motor:setDisminuirMarcha(disminuirMarchaFunc)
        self.disminuirMarcha = disminuirMarchaFunc
    end

    function Motor:setPuntoMuerto(puntoMuertoFunc)
        self.puntoMuerto = puntoMuertoFunc
    end


    return Motor
```

Ahora si queremos probar nuestra clase, utilizaremos la biblioteca antes mencionada **lunatest** para escribir las pruebas unitarias.

```Lua
    local lunatest = require("lunatest")
    local Motor = require("Motor")

    local m = Motor:new()

    function test_Encender()

        print("Encendemos el motor...")
        m:arrancar()
        lunatest.assert_equal(1, m:encendido())

        print("Aumentamos una marcha...")
        m:aumentarMarcha()
        lunatest.assert_equal(1, m:marchasMotor())

        print("Aumentamos una marcha mas...")
        m:aumentarMarcha()
        lunatest.assert_equal(2, m:marchasMotor())

        print("Disminuimos una marchas debido al tráfico...")
        m:disminuirMarcha()
        lunatest.assert_equal(1, m:marchasMotor())

        print("Encontramos aparcamiento, por lo que ponemos punto muerto...")
        m:puntoMuerto()
        lunatest.assert_equal(-1, m:marchasMotor())

        print("Apagamos el motor...")
        m:apagar()
        lunatest.assert_equal(0, m:encendido())

    end

    lunatest.run()
```

Inclusi si el usuario quiere agregar su propia implementación de las operaciones solo tiene que declararla en vez de usar el código anterior, tal como así:

```Lua
    
    print("Probamos a cambiar la polaridad de arrancar y encender el motor...")
    local customArrancar = function(x) Motor.turnOn = 0 end
    m:setArrancar(customArrancar)
    m:arrancar()
    lunatest.assert_equal(0, m:encendido())

    local customApagar = function(x) Motor.turnOn = 1 end
    m:setApagar(customApagar)
    m:apagar()
    lunatest.assert_equal(1, m:encendido())

```

# Conclusiones

Con la inyección de dependencias en Lua, podemos escribir un código más flexible y reutilizable que permite al usuario personalizar el comportamiento de la clase sin modificar directamente el código fuente de la clase.

# Anexo
Si se desea compilar el código debe instalar **lunatest**, para ello debe instalar los paquetes que le falta a su version de Lua, para ello introduce la siguiente linea de comando en la terminal:
```
    sudo apt install liblua5.3-dev
```
A continuación, debe instalar el paquete **LuaRocks** en el que se encuentra la biblioteca **lunatest**, para ello debe introducir las siguientes líneas de comando en su terminal:
```
    wget https://luarocks.org/releases/luarocks-3.9.2.tar.gz
    tar zxpf luarocks-3.9.2.tar.gz 
    cd luarocks-3.9.2
    ./configure && make && sudo make install
    sudo luarocks install luasocket
```
Al ver la palabra **luasocket** (descomponiendo: lua-socket), mas o menos se puede hacer una idea de que es lo que traerá consigo este paquete (concurrencia). Pero antes de entrar en detalles haremos el último paso, instalar por fin **lunatest**, para ello introduce la siguiente línea de comando en la terminal:
```
    sudo luarocks install lunatest
```
Después de esto ya podrás compilar el código del ejemplo solo con ejecutando el fichero test.lua (lua test.lua).
## Concurrencia de lunatest
Como dijimos antes, el paquete LuaRocks holía a concurrencia debido al uso de sockets. Y casualmente lunatest usa concurrencia con los test, es decir, para cada test le asocia un core, haciendolo más eficiente, el problema de nuestro ejemplo es que no estamos haciendo un uso controlado de la concurrencia, es decir, todos trabajan sobre una misma variable y acceden tanto para escritura como para lectura, por lo que pueden haber problemas, es por eso que hemos incluido todas las pruebas en una única función. Cabe resaltar que de hacer un buen uso de la exclusión mutua en concurrencia, podríamos hacer un problema super eficiente descomponiendo una función en tantas como comprobaciones se hagan, pero dado que este problema la complejidad es mínima probablemente una solución concurrente sea menos eficiente que una secuencial, que es por otro lado la que se ha propuesto.
Sí quiere conocer mas detalles sobre la biblioteca le dejo un enlace de github: https://github.com/silentbicycle/lunatest.