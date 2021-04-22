//package albums.api.script
//
//import org.intellij.lang.annotations.Language
//
//object Script {
//
//    object Create {
//        @Language("PostgreSQL")
//        const val USER = """
//            CREATE TABLE "USER"
//            (
//            id   UUID,
//    name VARCHAR(50) NOT NULL,
//    PRIMARY KEY (id)
//            );"""
//
//        @Language("PostgreSQL")
//        const val PRODUCT = """
//            CREATE TABLE product
//            (
//            id           int,
//            name         varchar(30)                     NOT NULL,
//            manufacturer varchar(30)                     NOT NULL,
//            color        varchar(20) DEFAULT 'Не задано' NOT NULL,
//            UNIQUE (name),
//            PRIMARY KEY (id)
//            );"""
//
//        @Language("PostgreSQL")
//        const val ORDER = """
//            CREATE TABLE "order"
//            (
//            id        int,
//            client_id INT REFERENCES client (id) NOT NULL,
//            date      DATE                       NOT NULL,
//            delivery  BOOLEAN DEFAULT false      NOT NULL,
//            archive   BOOLEAN DEFAULT false      NOT NULL,
//            PRIMARY KEY (id)
//            );"""
//
//        @Language("PostgreSQL")
//        const val ORDER_lINE = """
//            CREATE TABLE order_line
//            (
//            id         int,
//            order_id   INT REFERENCES "order" (id) NOT NULL,
//            product_id INT REFERENCES product (id) NOT NULL,
//            price      MONEY                       NOT NULL CHECK (price > 0::money),
//            quantity   INT                         NOT NULL DEFAULT 1,
//            discount   SMALLINT                    NOT NULL DEFAULT (0) CHECK ( discount >= 0 AND discount <= 100 ),
//            PRIMARY KEY (id)
//            );"""
//    }
//
//    object Drop {
//        @Language("PostgreSQL")
//        const val ORDER_LINE = """drop table if exists order_line;"""
//
//        @Language("PostgreSQL")
//        const val ORDER = """drop table if exists "order";"""
//
//        @Language("PostgreSQL")
//        const val PRODUCT = """drop table if exists product;"""
//
//        @Language("PostgreSQL")
//        const val CLIENT = """drop table if exists client;"""
//    }
//
//    object Insert {
//        @Language("PostgreSQL")
//        const val CLIENT = """
//            INSERT INTO client (id, name, email, city)
//            VALUES (1, 'ООО Ромашка', 'romashka.com', 'Москва'),
//            (2, 'ИП Иванов', 'ivanov.com', 'Санкт-Петербург'),
//            (3, 'ИП Сергеев', 'sergeev.com', 'Москва');"""
//
//        @Language("PostgreSQL")
//        const val PRODUCT = """
//            INSERT INTO product (id, name, manufacturer, color)
//            VALUES (1, 'Тетрадь в клетку 24 л.', 'ООО Лист', 'зеленый'),
//            (2, 'Дневник школьный', 'ООО Лист', 'синий'),
//            (3, 'Пенал', 'ООО Пеналмоторс', 'красный');"""
//
//        @Language("PostgreSQL")
//        const val ORDER = """
//            INSERT INTO "order" (id, client_id, date, delivery, archive)
//            VALUES (1, 2, '2021-03-01', true, false),
//            (2, 1, '2021-03-02', false, false),
//            (3, 1, '2021-03-03', true, false),
//            (4, 3, '2021-03-04', true, false),
//            (5, 3, '2021-03-05', true, false),
//            (6, 2, '2021-03-06', false, false),
//            (7, 1, '2021-03-07', true, false),
//            (8, 2, '2021-03-08', false, false),
//            (9, 3, '2021-03-09', true, false),
//            (10, 1, '2021-03-10', false, false);"""
//
//        @Language("PostgreSQL")
//        const val ORDER_LINE = """
//            INSERT INTO order_line (id, order_id, product_id, price, quantity, discount)
//            VALUES (1, 1, 2, 50, 1, 0),
//            (2, 1, 1, 20, 2, 5),
//            (3, 1, 3, 70, 1, 0),
//            (4, 2, 2, 40, 12, 50),
//            (5, 2, 1, 20, 2, 5),
//            (6, 3, 3, 10, 19, 40),
//            (7, 3, 2, 50, 1, 0),
//            (8, 3, 1, 20, 12, 50),
//            (9, 4, 2, 60, 1, 0),
//            (10, 4, 1, 10, 1, 0),
//            (11, 4, 3, 20, 5, 15),
//            (12, 5, 3, 70, 1, 0),
//            (13, 5, 2, 50, 1, 0),
//            (14, 6, 1, 21, 24, 50),
//            (15, 6, 3, 72, 1, 0),
//            (16, 6, 2, 48, 10, 30),
//            (17, 7, 1, 18, 7, 20),
//            (18, 8, 3, 67, 3, 5),
//            (19, 9, 2, 50, 1, 0),
//            (20, 9, 3, 20, 2, 5),
//            (21, 10, 3, 65, 10, 12),
//            (22, 10, 2, 54, 1, 0),
//            (23, 10, 1, 23, 23, 25),
//            (24, 10, 3, 73, 10, 5),
//            (25, 10, 2, 52, 13, 23),
//            (27, 10, 1, 26, 26, 60),
//            (28, 10, 3, 76, 18, 50);"""
//    }
//
//    @Language("PostgreSQL")
//    const val SELECT_CLIENT_WITH_ID = """
//        SELECT id, name, email, city
//        FROM client
//        WHERE id = ?;
//        """
//
//    @Language("PostgreSQL")
//    const val SELECT_CLIENTS_WHERE_CITY_NO_EQUALS = """
//        SELECT id, name, email, city
//        FROM client
//        WHERE city <> ?;
//        """
//
//    @Language("PostgreSQL")
//    const val GROUP_CLIENTS_BY_CITY = """
//        SELECT city, COUNT(*) AS city_count
//        FROM client
//        GROUP BY city;
//        """
//
//    @Language("PostgreSQL")
//    const val SORT_CLIENTS_BY_ID_DESC = """
//        SELECT id, name, email, city
//        FROM client
//        ORDER BY id DESC;
//        """
//
//    @Language("PostgreSQL")
//    const val SELECT_INFO_OF_CLIENTS_WITH_ORDERS = """
//        SELECT "order".id, "order".date, client.name
//        FROM "order"
//        JOIN client ON "order".client_id = client.id;
//        """
//
//    @Language("PostgreSQL")
//    const val SELECT_ORDER_WITH_ID = """
//        SELECT id, client_id, date, delivery, archive
//        FROM "order"
//        WHERE id = ?;
//        """
//
//    @Language("PostgreSQL")
//    const val SELECT_ORDERS_WHERE_DATE_AFTER = """
//        SELECT id, client_id, date, delivery, archive
//        FROM "order"
//        WHERE date > ?;
//        """
//
//    @Language("PostgreSQL")
//    const val GROUP_ORDERS_BY_CLIENTID = """
//        SELECT client_id, COUNT(*) AS order_count
//        FROM "order"
//        GROUP BY client_id;
//        """
//
//    @Language("PostgreSQL")
//    const val SORT_ORDERS_BY_DATE_DESC = """
//        SELECT id, client_id, date, delivery, archive
//        FROM "order"
//        ORDER BY date DESC;
//        """
//
//    @Language("PostgreSQL")
//    const val SELECT_ORDER_LINE_WHERE_ID = """
//        SELECT id, order_id, product_id, price, quantity, discount
//        FROM order_line
//        WHERE id = ?;
//        """
//
//    @Language("PostgreSQL")
//    const val SELECT_ORDERS_LINE_WHERE_PRICE_GRATER = """
//        SELECT id, order_id, product_id, price, quantity, discount
//        FROM order_line
//        WHERE price > ?::money;
//        """
//
//    @Language("PostgreSQL")
//    const val GROUP_ORDERS_LINE_BY_PRODUCT_ID = """
//        SELECT product_id, COUNT(*) AS count
//        FROM order_line
//        GROUP BY product_id
//        HAVING COUNT(*) >= ?;
//        """
//
//    @Language("PostgreSQL")
//    const val SORT_ORDERS_LINE_BY_DISCOUNT_DESC = """
//        SELECT id, order_id, product_id, price, quantity, discount
//        FROM order_line
//        ORDER BY discount DESC;
//        """
//
//    @Language("PostgreSQL")
//    const val SELECT_ORDERS_LINE_PRODUCT_INFO = """
//        SELECT order_line.price, product.name, o.date
//        FROM order_line
//        JOIN product ON order_line.product_id = product.id
//        JOIN "order" AS o on order_line.order_id = o.id;
//        """
//
//    @Language("PostgreSQL")
//    const val SELECT_PRODUCT_WITH_ID = """
//        SELECT id, name, manufacturer, color
//        FROM product
//        WHERE id = ?;
//        """
//
//    @Language("PostgreSQL")
//    const val SELECT_PRODUCTS_WHERE_ID_GRATER = """
//        SELECT id, name, manufacturer, color
//        FROM product
//        WHERE id > ?;
//        """
//
//    @Language("PostgreSQL")
//    const val GROUP_PRODUCTS_BY_MANUFACTURER = """
//        SELECT manufacturer, COUNT(*) AS product_count
//        FROM product
//        GROUP BY manufacturer;
//        """
//
//    @Language("PostgreSQL")
//    const val SORT_PRODUCTS_BY_ID_DESC = """
//        SELECT id, name, manufacturer, color
//        FROM product
//        ORDER BY id DESC;
//        """
//}