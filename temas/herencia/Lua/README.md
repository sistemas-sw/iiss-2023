# Herencia
## Herencia simple
Para la herencia rescatemos el ejemplo comentado en la encapsulación de la cuenta bancaria, recordemos que tenía una única variable que era el saldo y dos métodos modificadores uno para ingresar dinero (*deposit*) y otro para retirar dinero (*withdraw*), cabe resaltar que la clase la declaramos con el nombre *Account*. Ahora queremos tener una subclase *SpecialAccount* que permita al cliente retirar mas dinero del que tiene en la cuenta. Empezaremos con una clase vacía que simplemente herede todas las operaciones de la clase base (*Account*).
```Lua
    SpecialAccount = Account:new()
```

Hasta ahora, *SpecialAccount* era solo una instancia de *Account*. Lo bueno viene ahora:
```Lua
    s = SpecialAccount:new{limit=1000.00}
```
*SpecialAccount* hereda *new* de *Account* como cualquier otro método. Esta vez, además, cuando *new* se ejecuta, su parámetro *self* se referirá a *SpecialAccount*. No obstante, la metatabla de *s* sera *SpecialAccount*, lo cuál su valor en *__index* también es *SpecialAccount*. Entonces, *s* hereda de *SpecialAccount*, la cuál hereda de *Account*. Ahora evaluemos la siguiente línea de código:
```Lua
    s:deposit(100.00)
```
Lua no va a encontrar una definición de *deposit* en *s*, por lo que mirará dentro de *SpecialAccount*, tampoco encontrará el método *deposit* allí, por lo que mirará en la clase base original (*Account*) y allí encontrará la implementación original de *deposit*.
Lo que hace especial a la subclase *SpecialAccount* es que puede redefinir cualquier método heredado de la superclase sin que ésta haya marcado que los métodos pueden ser redefinidos. Todo lo que tenemos que hacer es escribir el nuevo método:
```Lua
    function SpecialAccount:withdraw(v)
        if v - self.cantidad >= self:getLimit() then
            error"saldo insuficiente"
        end
        self.cantidad = self.cantidad - v
    end

    function SpecialAccount:getLimit()
        return self.limit or 0
    end
```
Ahora cuando hagamos una llamada a la función *withdraw* con *s* tal que:
```Lua
    s:withdraw(200.00)
```
Lua no irá a la clase *Account*, ya que encontrará un nuevo método *withdraw* en *SpecialAccount* antes de que pueda ir a la superclase. Debido a que **s.limit** es 1000.00, el programa hará el reintegro aunque saquemos más dinero que el saldo actual dejando así el saldo de la cuenta en negativo.
En Lua es interesante resaltar que podemos dotar de un nuevo comportamiento a una clase sin necesidad de crear otra. Es decir, si solo un objeto necesita un comportamiento específico, se puede dotar a ese objeto de ese comportamiento sin tener que cambiar la clase. Por ejemplo, si la cuenta **s** rerpresenta una funcionalidad específica para el cliente, solo tendríamos que añadir:
```Lua
    function s:getLimit()
        return self.cantidad * 0.10
    end
```
Después de la declaración anterior, al hacer la llamada **s:withdraw(200.00)** elige el método de *SpecialAccount*, pero cuando el método **withdraw** llega a la línea **self:getLimit** es la declaración que hemos definido anteriormente la que se ejecuta.

Debido a que los objetos no son propios de Lua, hay varias formas de realizar programación orientada a objetos en Lua. Una aproximación es la forma que acabamos de ver, pero hay muchas más. 

## Herencia múltiple

Cuando hablamos de herencia múltiple significa que puede tener mas de una superclase. Para este caso, nosotros podemos tener una función que se llame **createClass**, la cuál tendrá como argumento la superclase de la nueva clase.
Esta función creará una tabla que representará la nueva clase y declarará su metatabla con el metamétodo *__index* que realiza la herencia múltiple. A pesar de la herencia múltiple, cada instancia de objeto todavía pertenece a una sola clase, donde busca todos sus métodos. Por lo tanto, la relación entre clases y superclases es diferente de la relación entre clases e instancias. Particularmente, una clase no puede ser la metatabla para sus instancias y para sus subclases al mismo tiempo. Veamos un ejemplo, para ello rescatemos la clase *Account* y crearemos una nueva clase *Named*

```Lua
    Named = {}
    function Named:getname ()
        return self.name
    end
    function Named:setname (n)
        self.name = n
    end

    -- Buscar 'k' en la lista 'plist'
    local function search (k, plist)
        for i=1, #plist do
            local v = plist[i][k]   
            if v then
                return v
            end
        end
    end

    function createClass (...)
        local c = {}        --nueva clase
        local parents = {...}

        --la clase buscará por cada metodo en la lista de sus padres
        setmetatable(c, {__index = function (t, k)
            return search(k, parents)
        end})

        --prepara 'c' para ser una metatabla de sus instancias.
        c.__index = c

        --define el nuevo constructor para esta nueva clase
        function c:new (o)
            o = o or {}
            setmetatable(o, c)
            return o
        end

        return c        --devuelve la nueva clase
    
    end
```

Una vez definida la función que nos permite heredar de múltiples clases, para crear una nueva clase llamada *NamedAccount* la cuál es una subclase de las clases *Account* y *Named*, simplemente llamamos al método *createClass*:
```Lua
    NamedAccount = createClass(Account, Named)
```
Y para crear y usaar instancias, normalmente usamos:
```Lua
    account = NamedAccount:new{name = "Paul"}
    print(account:getname())       
```
Como es lógico Lua no podrá encontrar la función *getname* en *account*, por lo que mirará en el *__index* de la metatabla de  *account*, la cuál es *NamedAccount*. Pero *NameAccount* tampoco contiene la función *getname*, por lo que volverá a mirar en el *__index* de la metatabla esta vez de *NameAccount*. Debido a que se encuentra allí, Lua la llamará. Repasando, *getname* se mirará primero en la instancia *(account)*, sin éxito, luego mira en *NamedAccount* donde encontrará un valor no nulo, lo cuál será el final de la búsqueda.
Debido a la profunda complejidad de la búsqueda, los casos de herencia múltiple no son como los de herencia simple. Podemos usar la herencia de metodos en las subclases para bajar la complejidad, tal que así:

```Lua
    setmetatable(c, {__index = function(t, k)
        local v = search(k, parents)
        t[k] = v    --guarda para el siguiente acceso
        return v
    end})
```
Con este truco, accederemos a los métodos de la clase heredada tan rápido como a los métodos locales (dejando de lado el primer acceso). La desventaja es la complejidad de cambiar la definición del método después de que el sistema arranque, debido a que estos cambios no se propagan con la herencia.

## Conclusiones

Para terminar, tenemos que decir que en Lua la herencia es muy fácil de implementar en Lua, y se han visto formas incluso de optimizar las llamadas a las funciones de las clases heredadas, lógicamente avandonando la facilidad de mantenimiento.