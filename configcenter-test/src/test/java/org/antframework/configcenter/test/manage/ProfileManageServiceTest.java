/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2017-09-03 16:40 创建
 */
package org.antframework.configcenter.test.manage;

import org.antframework.common.util.facade.Status;
import org.antframework.configcenter.facade.api.manage.ProfileManageService;
import org.antframework.configcenter.facade.order.manage.AddOrModifyProfileOrder;
import org.antframework.configcenter.facade.order.manage.DeleteProfileOrder;
import org.antframework.configcenter.facade.order.manage.FindProfileOrder;
import org.antframework.configcenter.facade.order.manage.QueryProfileOrder;
import org.antframework.configcenter.facade.result.manage.AddOrModifyProfileResult;
import org.antframework.configcenter.facade.result.manage.DeleteProfileResult;
import org.antframework.configcenter.facade.result.manage.FindProfileResult;
import org.antframework.configcenter.facade.result.manage.QueryProfileResult;
import org.antframework.configcenter.test.AbstractTest;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
@Ignore
public class ProfileManageServiceTest extends AbstractTest {
    @Autowired
    private ProfileManageService profileManageService;

    @Test
    public void testAddOrModifyProfile() {
        AddOrModifyProfileOrder order = new AddOrModifyProfileOrder();
        order.setProfileCode("dev");
        order.setMemo("开发环境");
        AddOrModifyProfileResult result = profileManageService.addOrModifyProfile(order);
        checkResult(result, Status.SUCCESS);
    }

    @Test
    public void testDeleteProfile() {
        testAddOrModifyProfile();
        DeleteProfileOrder order = new DeleteProfileOrder();
        order.setProfileCode("dev");
        DeleteProfileResult result = profileManageService.deleteProfile(order);
        checkResult(result, Status.SUCCESS);
    }

    @Test
    public void testFindProfile() {
        testAddOrModifyProfile();
        FindProfileOrder order = new FindProfileOrder();
        order.setProfileCode("dev");
        FindProfileResult result = profileManageService.findProfile(order);
        checkResult(result, Status.SUCCESS);
        Assert.assertNotEquals(null, result.getProfileInfo());
        Assert.assertEquals("dev", result.getProfileInfo().getProfileCode());
    }

    @Test
    public void testQueryProfile() {
        testAddOrModifyProfile();
        QueryProfileOrder order = new QueryProfileOrder();
        order.setPageNo(1);
        order.setPageSize(10);
        order.setProfileCode("dev");
        QueryProfileResult result = profileManageService.queryProfile(order);
        checkResult(result, Status.SUCCESS);
        Assert.assertEquals(1, result.getInfos().size());
    }
}