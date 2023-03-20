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