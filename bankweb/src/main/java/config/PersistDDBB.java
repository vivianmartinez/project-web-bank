package config;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import model.entity.Account;
import model.entity.Entity;
import validator.customexceptions.InvalidInsertSQLException;
import validator.customexceptions.InvalidUpdateSQLException;

public class PersistDDBB {

    private Connection connection;
    Credentials credentials = new Credentials();

    public PersistDDBB() {
        this.connection = connect();
    }

    /**
     * Establece conexión con la base de datos
     * 
     * @Author Vivian Martínez
     * @return Connection conexión con base de datos
     */
    public Connection connect() {
        Connection conn = null;
        try {
            Class.forName(credentials.driver);
            conn = DriverManager.getConnection(credentials.url, credentials.userName, credentials.password);
        } catch (ClassNotFoundException | SQLException e) {
            // e.printStackTrace();
            System.out.println("Cannot connect to database");
        }
        return conn;
    }

    /**
     * Ejecuta Select con sentencia no preparada
     * 
     * @Author Vivian Martínez
     * @param sqlQuery String con sentencia SQL Select
     * @return ArrayList con resultado de la consulta
     */
    public ArrayList<HashMap> executeSelectSQL(String sqlQuery) {
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            // creamos ArrayList con cada registro usando método personalizado
            ArrayList<HashMap> data = rsToArrayList(rs);
            stmt.close();
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Devuelve un ArrayList con elementos HashMap que representan una entidad *
     * 
     * @Author Vivian Martínez
     * @param rs ResultSet obtenido de ejecutar una sentencia SQL
     * @throws SQLException
     * @return ArrayList<HashMap> con datos de la entidad según columna de tabla y
     *         valor
     */
    @SuppressWarnings("unchecked")
    public ArrayList<HashMap> rsToArrayList(ResultSet rs) throws SQLException {
        ResultSetMetaData rsMeta = rs.getMetaData();
        ArrayList<HashMap> data = new ArrayList<>();
        int number_columns = rsMeta.getColumnCount();
        while (rs.next()) {
            HashMap resultHash = new HashMap<>();
            try {
                for (int i = 1; i <= number_columns; i++) {
                    String column_value = rs.getString(rsMeta.getColumnName(i));
                    resultHash.put(rsMeta.getColumnName(i), column_value);
                }
                data.add(resultHash);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        rs.close();
        return data;
    }

    /**
     * Sentencia SQL SELECT preparada con condición WHERE
     * 
     * @param sqlQuery
     * @param whereParams
     * @return ArrayList con uno o más elementos HashMap que cumplen una condición -
     *         solo operador AND entre condiciones
     */
    public ArrayList<HashMap> executeSelectSQLPrepared(Entity entity, HashMap whereParams, String select) {
        String sqlQuery = "SELECT " + select + " FROM " + entity.getClass().getSimpleName();
        Object[] keys = whereParams.keySet().toArray();
        String where = " WHERE ";
        where = where.concat((String) keys[0]).concat(" = ").concat("?");

        // si el hashmap trae más de 1 elemento entonces agregar AND
        if (keys.length > 1) {
            for (int i = 1; i < whereParams.size(); i++) {
                where = where.concat(" AND ");
                where = where.concat((String) keys[i]).concat(" = ").concat("?");
            }
        }
        sqlQuery = sqlQuery.concat(where);

        try {
            PreparedStatement stmtPrepared = this.connection.prepareStatement(sqlQuery);
            // primer prepare field
            if (keys[0] != "id") {
                stmtPrepared.setString(1, whereParams.get(keys[0]).toString());
            } else {
                stmtPrepared.setInt(1, (int) whereParams.get(keys[0]));
            }

            if (keys.length > 1) {
                for (int i = 1; i < keys.length; i++) {
                    if (keys[i] != "id") {
                        stmtPrepared.setString(i + 1, whereParams.get(keys[i]).toString());
                    } else {
                        stmtPrepared.setInt(i + 1, (int) whereParams.get(keys[i]));
                    }
                }
            }
            ResultSet rs = stmtPrepared.executeQuery();
            // guardar en un arrayList el resultSet
            ArrayList<HashMap> data = rsToArrayList(rs);
            // cerrar statememt
            stmtPrepared.close();
            // cerrar resultSet
            rs.close();
            return data;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Inserta datos de forma genérica en cualquier tabla de la base de
     * datos.
     * 
     * @Author Vivian Martínez
     * @param entity Entidad con propiedades seteadas
     * @throws SQLException
     * @throws InvalidInsertSQLException
     */
    public int executeStatementPreparedSQLInsert(Entity entity) throws SQLException, InvalidInsertSQLException {
        // si entity no es nulo crear sentencia INSERT
        if (entity != null) {
            // verificamos la cantidad de atributos o propiedades
            String sql = "INSERT INTO " + entity.getClass().getSimpleName().toLowerCase();
            String columns = "(";
            String params = " VALUES(";

            for (Method method : entity.getClass().getDeclaredMethods()) {
                if (method.getName().startsWith("get")) {
                    Object property_value = new Object();
                    try {
                        // getName
                        property_value = method.invoke(entity);
                        if (property_value != null && method.getName() != "getId") {
                            columns = columns.concat(method.getName().substring(3).toLowerCase()).concat(",");
                            params = params.concat("?,");
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            columns = columns.substring(0, columns.length() - 1) + ") ";
            params = params.substring(0, params.length() - 1) + ")";
            sql = sql.concat(columns).concat(params);

            PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int num_param = 0;
            for (Method method : entity.getClass().getDeclaredMethods()) {
                if (method.getName().startsWith("get")) {
                    Object property_value = new Object();
                    try {
                        property_value = method.invoke(entity);
                        if (property_value != null && method.getName() != "getId") {
                            num_param++;
                            String type = method.getReturnType().getSimpleName();
                            switch (type) {
                                case "int":
                                    stmt.setInt(num_param, (int) property_value);
                                    break;
                                case "long":
                                    stmt.setLong(num_param, (long) property_value);
                                    break;
                                case "Double":
                                    stmt.setDouble(num_param, (Double) property_value);
                                    break;
                                case "Boolean":
                                    stmt.setBoolean(num_param, (Boolean) property_value);
                                    break;
                                case "Float":
                                    stmt.setFloat(num_param, (Float) property_value);
                                    break;
                                default:
                                    // se guardan como string tipos String - LocalDate
                                    stmt.setString(num_param, property_value.toString());
                                    break;
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            int affectedRow = stmt.executeUpdate();
            ResultSet rsGeneratedKeys = stmt.getGeneratedKeys();
            if (rsGeneratedKeys.next()) {
                return rsGeneratedKeys.getInt(1);
            }
            stmt.close();
        } else {
            throw new InvalidInsertSQLException("Invalid SQL INSERT");
        }
        return 0;
    }

    /**
     * Actualiza datos de forma genérica en cualquier tabla de la base de
     * datos.
     * 
     * @Author Vivian Martínez
     * @param entity Entidad con propiedades seteadas
     * @throws SQLException
     * @throws InvalidInsertSQLException
     */
    public int executeStatementPreparedSQLUpdate(Entity entity) throws SQLException, InvalidUpdateSQLException {
        System.out.println("actualizar");
        int affectedRow = 0;
        // si entity no es nulo crear sentencia UPDATE
        if (entity != null) {
            // verificamos la cantidad de atributos o propiedades
            String sql = "UPDATE " + entity.getClass().getSimpleName().toLowerCase() + " SET ";
            String columns = "";
            String where = " WHERE id = ?";
            int idUpdate = 0;
            for (Method method : entity.getClass().getDeclaredMethods()) {
                if (method.getName().startsWith("get")) {
                    Object property_value = new Object();
                    try {
                        // getName Method
                        property_value = method.invoke(entity);
                        if (property_value != null && method.getName() != "getId") {
                            columns = columns.concat(method.getName().substring(3).toLowerCase()).concat(" = ?,");
                        }
                        if (method.getName() == "getId") {
                            idUpdate = (int) property_value;
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (idUpdate != 0) {
                columns = columns.substring(0, columns.length() - 1);
                sql = sql.concat(columns).concat(where);
                System.out.println(sql);
                PreparedStatement stmt = setParametersPreparedStatement(entity, sql);
                // getParametersMetaData para obtener la cantidad de parámetros que se pasan
                stmt.setInt(stmt.getParameterMetaData().getParameterCount(), idUpdate);

                affectedRow = stmt.executeUpdate();
                stmt.close();
            } else {
                throw new InvalidUpdateSQLException("INVALID SQL UPDATE, you must send Id.");
            }

        } else {
            throw new InvalidUpdateSQLException("INVALID SQL UPDATE");
        }
        return affectedRow;
    }

    /**
     * Devuelve la sentencia preparada con los parámetros seteados de acuerdo al
     * tipo
     * 
     * @Author Vivian Martínez
     * @param entity
     * @param sql
     * @return Prepare Statement with parameters with set parameters
     * @throws SQLException
     */
    public PreparedStatement setParametersPreparedStatement(Entity entity, String sql) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        int num_param = 0;
        for (Method method : entity.getClass().getDeclaredMethods()) {
            if (method.getName().startsWith("get")) {
                Object property_value = new Object();
                try {
                    property_value = method.invoke(entity);
                    if (property_value != null && method.getName() != "getId") {
                        num_param++;
                        String type = method.getReturnType().getSimpleName();
                        switch (type) {
                            case "int":
                                stmt.setInt(num_param, (int) property_value);
                                break;
                            case "long":
                                stmt.setLong(num_param, (long) property_value);
                                break;
                            case "Double":
                                stmt.setDouble(num_param, (Double) property_value);
                                break;
                            case "Boolean":
                                stmt.setBoolean(num_param, (Boolean) property_value);
                                break;
                            case "Float":
                                stmt.setFloat(num_param, (Float) property_value);
                                break;
                            default:
                                // se guardan como string tipos String - LocalDate
                                stmt.setString(num_param, property_value.toString());
                                break;
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return stmt;
    }

    /**
     * Ejecuta transacción
     * 
     * @param sqlStmts ArrayList de sentencias sql a ejecutar en transacción
     * @return Boolean
     */
    public Boolean executeStatementTransaction(ArrayList<String> sqlStmts) {
        try {
            Statement stmt = this.connection.createStatement();
            this.connection.setAutoCommit(false);
            // recorremos las sentencias sql
            for (int i = 0; i < sqlStmts.size(); i++) {
                stmt.executeQuery(sqlStmts.get(i));
            }
            this.connection.commit();
            return true;

        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Integer> executeTransactionEntities(ArrayList<Entity> entities) throws InvalidInsertSQLException {
        ArrayList<Integer> lastInserts = new ArrayList<>();
        try {
            this.connection.setAutoCommit(false);
            int idInsert = this.executeStatementPreparedSQLInsert(entities.get(0));
            lastInserts.add(idInsert);
            if (idInsert != 0) {
                if (entities.get(1) instanceof Account) {
                    ((Account) entities.get(1)).setCustomer_id(idInsert);
                }

                lastInserts.add(this.executeStatementPreparedSQLInsert(entities.get(1)));
            }
            this.connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastInserts;
    }

    /**
     * close connection
     */
    public void closeConnection() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
