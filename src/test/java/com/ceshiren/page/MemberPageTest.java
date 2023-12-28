package com.ceshiren.page;


import com.ceshiren.page.entity.User;
import com.ceshiren.util.FakerUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

class MemberPageTest {
    public static ConcatPage concatPage;

    @Test
    void addMemberTrue() {
        User user = new User(FakerUtil.get_name(),FakerUtil.get_acctid(),FakerUtil.get_zh_phone());
        concatPage = new MainPage()
                        .toConcatPage()
                        .clickAddMember()
                        .addMemberTrue(user);

        List<String> memberList = concatPage.getMemberList();
                assertThat("成员添加失败",memberList.contains(user.getUsername()));
    }

    @Test
    void addMemberFalse() {

    }

    @AfterAll
    static void tearDownClass(){
        concatPage.quite();
    }
}