package com.vimensa.core.serviceimp;

import com.vimensa.apis.responses.UserResponse;
import com.vimensa.config.ErrorCode;
import com.vimensa.core.dao.UserData;
import com.vimensa.core.pools.HikariPool;
import com.vimensa.core.service.UserService;

import java.sql.*;
import java.util.Calendar;

import static java.sql.Types.INTEGER;

public class UserServiceImp implements UserService {
    @Override
    public UserResponse login(UserData userData) throws SQLException {
        Connection connection = HikariPool.getConnection();
        UserResponse response = new UserResponse();
        if (userData.getPhone() == null || userData.getPassword() == null) {
            response.setError(ErrorCode.INVALID_PARAMS);
        } else {
            try {
                String query = "SELECT * from tb_user where phone=?";
                PreparedStatement st = connection.prepareStatement(query);
                st.setString(1, userData.getPhone());
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    String password = rs.getString("password");
                    if (!password.equals(userData.getPassword())) {
                        response.setError(ErrorCode.INVALID_PASSWORD);
                    } else {
                        userData.setId(rs.getInt("id_user"));
                        userData.setPhone(rs.getString("phone"));
                        userData.setName(rs.getString("name"));
                        response.setUserData(userData);
                    }
                } else {
                    response.setError(ErrorCode.ACCOUNT_NOT_EXIST);
                }
                st.close();
            } catch (Exception e) {
                response.setError(ErrorCode.SYSTEM_ERROR);
            } finally {
                connection.close();
            }
        }
        return response;
    }

    @Override
    public UserResponse register(UserData userData) throws SQLException {
        Connection connection = HikariPool.getConnection();
        UserResponse response = new UserResponse();
        try {
            Calendar now = Calendar.getInstance();
            userData.setTimeCreated(now);
            long timestamp = now.getTimeInMillis();
            String query = "CALL create_user_data(?,?,?,?,?)";
            CallableStatement st = connection.prepareCall(query);
            st.setString(1, userData.getPhone());
            st.setString(2, userData.getPassword());
            st.setString(3, userData.getName());
            st.setTimestamp(4, new Timestamp(timestamp));
            st.registerOutParameter(5, INTEGER);
            st.executeUpdate();
            int checkUsername = st.getInt(5);
            if (checkUsername==1){
                response.setUserData(userData);
            }else{
                response.setError(ErrorCode.DUPLICATE_ACCOUNT);
            }
            st.close();
        } catch (Exception e) {
            response.setError(ErrorCode.SYSTEM_ERROR);
        } finally {
            connection.close();
        }
        return response;
    }

}
