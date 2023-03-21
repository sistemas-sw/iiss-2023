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