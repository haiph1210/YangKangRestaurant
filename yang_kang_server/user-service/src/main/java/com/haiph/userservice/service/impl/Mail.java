package com.haiph.userservice.service.impl;

public interface Mail {
    final String SUBJECT = "Chào mừng bạn đến với Yang Kang Restaurant <3";
    final String MESSAGE = "Chúc mừng bạn đã đăng kí thành công trở thành user của YangKang Restautrant," +
            "\n để sử dụng được tài khoản,hãy click vào đường link bên dưới.\n";
    final String CONFIRM = "http://localhost:8001/user/active?personcode=";


}
