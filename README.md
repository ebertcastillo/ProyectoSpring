# Proyecto Final · E-commerce con Spring Boot y Kafka


# Nombre: EBERT CASTILLO CORTEZ

# se tiene los tres modelos

# 1. product-service   (los paquetes son dev.ebecast.product_service)   link: 
# 2. order-service     (los paquetes son dev.ebecast.order_service)     link:
# 3. inventory-service (los paquetes son dev.ebecast.inventory_service) link:

# Se creo el Proyecto desde 0 con los pasos y cheatsheet proporcionados con el docente se trato de realizar lo mismo
# se agrego algunos mapper y execption en el proyecto.


# Primer Paso se genero el Proyecto Product Service con todos sus procesos.

![Imagen de contenedor descargada](screenshots/1.png)

![Imagen de contenedor descargada](screenshots/2.png)

**Se configuro Docker para la Base de Datos y se levanto la misma**

![Imagen de contenedor descargada](screenshots/3.png)

![Imagen de contenedor descargada](screenshots/4.png)

**Se configuro kafka y se hizo un test**

![Imagen de contenedor descargada](screenshots/5.png)

![Imagen de contenedor descargada](screenshots/6.png)

**Se configuro los topics siguientes**

![Imagen de contenedor descargada](screenshots/7.png)

**Se ejecuta el proyecto Product_Service para realizar pruebas correspondientes**
**Las pruebas la realizamos con POSTMAN de la siguiente manera**

***Crea y Lista de categorias**

![Imagen de contenedor descargada](screenshots/8.png)

![Imagen de contenedor descargada](screenshots/9.png)

***Crea y Lista de productos**

![Imagen de contenedor descargada](screenshots/10.png)

![Imagen de contenedor descargada](screenshots/11.png)

![Imagen de contenedor descargada](screenshots/12.png)

![Imagen de contenedor descargada](screenshots/13.png)

![Imagen de contenedor descargada](screenshots/14.png)

**Se verifica mediante los topics lo realizado**

![Imagen de contenedor descargada](screenshots/15.png)

## Segundo Paso Se crea el proyecto order-service donde se modifico y se agrego nuevas excepciones (exception) y se genero el mapper para obtener un codigo limpio

![Imagen de contenedor descargada](screenshots/16.png)

![Imagen de contenedor descargada](screenshots/16.2.png)

**Se configura el aplication.yml y tambian las variables de entorno para la coneccion con product-service**

![Imagen de contenedor descargada](screenshots/17.png)

**Se crea la base de datos en el servicoo de product-service**

![Imagen de contenedor descargada](screenshots/18.png)


**Se configura kafka y se ejecuta el proyecto order-service para realizar pruebas correspondientes**
**Las pruebas la realizamos con POSTMAN de la siguiente manera**

**Se crea las ordenes y se lista**

![Imagen de contenedor descargada](screenshots/19.png)

![Imagen de contenedor descargada](screenshots/20.png)

![Imagen de contenedor descargada](screenshots/21.png)

![Imagen de contenedor descargada](screenshots/22.png)

![Imagen de contenedor descargada](screenshots/23.png)

**En produc-service se verifica las categorias y productos registrados anteriormente**

![Imagen de contenedor descargada](screenshots/24.png)

**Tambien se puede ver los registros de ordenes que se ejecuto desde order-service**

![Imagen de contenedor descargada](screenshots/25.png)

**En el topíc de kafka ecommerce.orders.placed se verifica lo registrado**

![Imagen de contenedor descargada](screenshots/26.png)

## Tecer Paso Se crea el proyecto inventory-service donde se modifico y se agrego nuevas excepciones (exception) y se genero el mapper para obtener un codigo limpio

![Imagen de contenedor descargada](screenshots/29.png)

![Imagen de contenedor descargada](screenshots/29.2.png)

**Se crea la base de datos de inventory-service en el servicio de product-service**

![Imagen de contenedor descargada](screenshots/27.png)

![Imagen de contenedor descargada](screenshots/28.png)


**Se configura kafka y se ejecuta el proyecto inventory-service para realizar pruebas correspondientes**
**Las pruebas la realizamos con POSTMAN de la siguiente manera**

![Imagen de contenedor descargada](screenshots/30.png)

![Imagen de contenedor descargada](screenshots/31.png)

![Imagen de contenedor descargada](screenshots/32.png)

![Imagen de contenedor descargada](screenshots/33.png)

![Imagen de contenedor descargada](screenshots/34.png)

![Imagen de contenedor descargada](screenshots/35.png)

![Imagen de contenedor descargada](screenshots/36.png)

![Imagen de contenedor descargada](screenshots/37.png)

**Verificamos los registros en la tabla**

![Imagen de contenedor descargada](screenshots/38.png)

