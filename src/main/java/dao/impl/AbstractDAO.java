package dao.impl;

import dao.GenericDAO;
import mapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractDAO<T> implements GenericDAO<T> {


    public Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://DESKTOP-PSQ7FI6\\SQLEXPRESS:1433;databaseName=PRJ_Assignment";
            String username = "sa";
            String password = "1";
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }

    public void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
        List<T> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            setParameter(statement, parameters);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(rowMapper.mapRow((resultSet)));
            }
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                assert connection != null;
                connection.close();
                assert statement != null;
                statement.close();
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        return results;
    }

    @Override
    public void update(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParameter(statement, parameters);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        } finally {
            try {
                assert connection != null;
                connection.close();
                assert statement != null;
                statement.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }

    @Override
    public Integer insert(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Integer id = null;
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
            setParameter(statement, parameters);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            connection.commit();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        } finally {
            assert connection != null;
            assert statement != null;
            assert resultSet != null;
            closeConnection(connection, statement, resultSet);
        }
        return null;
    }

    private void setParameter(PreparedStatement statement, Object... parameters) {
        int index;
        try {
            for (int i = 0; i < parameters.length; i++) {
                Object parameter = parameters[i];
                index = i + 1;  // because the order of sql statements count from 1
                if (parameter instanceof Long) {
                    statement.setLong(index, (Long) parameter);
                } else if (parameter instanceof String) {
                    statement.setString(index, (String) parameter);
                } else if (parameter instanceof Integer) {
                    statement.setInt(index, (Integer) parameter);
                } else if (parameter instanceof Timestamp) {
                    statement.setTimestamp(index, (Timestamp) parameter);
                } else if (parameter instanceof Float) {
                    statement.setFloat(index, (Float) parameter);
                } else if (parameter instanceof Double) {
                    statement.setDouble(index, (Double) parameter);
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public int count(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            int count = 0;
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            setParameter(statement, parameters);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            assert connection != null;
            assert statement != null;
            assert resultSet != null;
            closeConnection(connection, statement, resultSet);
        }
    }
}
