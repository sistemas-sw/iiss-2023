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


    print("Probamos a cambiar la polaridad de arrancar y encender el motor...")
    local customArrancar = function(x) Motor.turnOn = 0 end
    m:setArrancar(customArrancar)
    m:arrancar()
    lunatest.assert_equal(0, m:encendido())

    local customApagar = function(x) Motor.turnOn = 1 end
    m:setApagar(customApagar)
    m:apagar()
    lunatest.assert_equal(1, m:encendido())

end

lunatest.run()