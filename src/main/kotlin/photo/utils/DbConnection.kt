//package albums.api.utils
//
//import org.apache.catalina.mapper.Mapper
//import java.sql.Connection
//import java.sql.DriverManager
//
//class DbConnection : AutoCloseable {
//    private val connection: Connection = DriverManager.getConnection(
//        "jdbc:postgresql://localhost:5432/Course project",
//        "maria",
//        "123"
//    )
//
//    /**
//     *Выполняет запрос [sql]: CREATE, INSERT, DELETE
//     */
//    fun executeNonQuery(sql: String) {
//        connection.createStatement().use { statement ->
//            statement.executeUpdate(sql)
//        }
//    }
//
//    /**
//     *Выполняет запрос [sql]: SELECT и возвращает объект типа [T?]
//     */
//    fun <T> executeQueryForObject(sql: String, mapper: Mapper<T>, vararg parameters: Any): T? {
//        return executeQuery(sql, mapper, *parameters).firstOrNull()
//    }
//
//    /**
//     *Выполняет запрос [sql]: SELECT и возвращает список объектов типа [T]
//     */
//    fun <T> executeQuery(sql: String, mapper: Mapper<T>, vararg parameters: Any): List<T> {
//        val result = mutableListOf<T>()
//        connection.prepareStatement(sql).use { statement ->
//            parameters.forEachIndexed { index, parameter -> statement.setObject(index + 1, parameter) }
//            statement.executeQuery().use { resultSet ->
//                while (resultSet.next()) {
//                    result.add(mapper.writeResultSetToClass(resultSet))
//                }
//            }
//        }
//        return result
//    }
//
//    override fun close() {
//        connection.close()
//    }
//}