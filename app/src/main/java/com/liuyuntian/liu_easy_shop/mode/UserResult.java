package com.liuyuntian.liu_easy_shop.mode;

/**
 * Created by liuyu on 2017/6/28.
 */

public class UserResult {
    /**
     * code : 1
     * msg : succeed
     * data : {"username":"liu1123","name":"yt80fdfbe2b3dc4f56ba54b16d0731770e","uuid":"D05AFEF3C4D9454DA2E988EE7C03E7D6","password":"123456"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * username : liu1123
         * name : yt80fdfbe2b3dc4f56ba54b16d0731770e
         * uuid : D05AFEF3C4D9454DA2E988EE7C03E7D6
         * password : 123456
         */

        private String username;
        private String name;
        private String uuid;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
