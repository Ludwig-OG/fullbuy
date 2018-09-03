package com.we.fullbuy.serviceImpl;


import com.we.fullbuy.dao.ManagerMapper;
import com.we.fullbuy.pojo.Manager;
import com.we.fullbuy.service.managerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class managerServiceImpl implements managerService {
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    @Transactional
    public int login(int managerId, String password) {
        Manager manager = new Manager();
        manager = managerMapper.selectByPrimaryKey(managerId);
        if (manager!=null&&manager.getManagerpwd().equals(password))
            return 1;
        else
            return 0;
    }
}
