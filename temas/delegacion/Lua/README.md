# Delegación en Lua

La delegación es un patrón de diseño en el que un objeto transfiere la responsabilidad de una tarea a otro objeto. En Lua, podemos implementar la delegación mediante metatablas. Ya conocemos las metatablas de su uso en la **encapsulación** y en la **herencia**. Si ha visto la encapsulación y herencia en Lua, no tendrá dificultades para comprender la delegación, la cuál es muy simple de ver:

```Lua
    local persona = {
        nombre = function(self, nom)
            print("Mi nombre es: ", nom)
        end
    }

    --Creamos el objeto al que vamos a delegar la funcion
    local hombre = {}

    --Asignamos la metatabla al objeto delegado
    setmetatable(hombre, {__index = persona})

    --Llamamos al método de la metatabla como si fuera un método propio
    hombre:nombre("Pedro")
```
Este ejemplo es muy basico, pero podemos complicarlo muchísimo más, 
Debemos resaltar que la delegación en Lua a través de sus metatablas puede tener un gran impacto en el rendimiento, ya que requiere el uso de la metaprogramación. Por lo tanto, debe asegurarse que se implemente de forma eficiente.