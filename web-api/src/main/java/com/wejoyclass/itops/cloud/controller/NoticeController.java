package com.wejoyclass.itops.cloud.controller;

import com.wejoyclass.core.page.Page;
import com.wejoyclass.core.page.QueryParameter;
import com.wejoyclass.core.params.Request;
import com.wejoyclass.core.util.CtrlUtil;
import com.wejoyclass.core.util.MapperUtil;
import com.wejoyclass.core.util.RespEntity;
import com.wejoyclass.itops.cloud.dto.NoticeRuleDto;
import com.wejoyclass.itops.cloud.dto.NoticeRuleQueryParam;
import com.wejoyclass.itops.cloud.entity.NoticeRule;
import com.wejoyclass.itops.cloud.entity.WarningMsgLicense;
import com.wejoyclass.itops.cloud.service.NoticeRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "通知规则设置")
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeRuleService noticeRuleService;

    @ApiOperation("根据id查询通知规则")
    @GetMapping("/noticeRules/{id}")
    public RespEntity<NoticeRule> getNoticesById(@PathVariable("id") Long id){
        return CtrlUtil.exe(r -> r.setVal(noticeRuleService.getNoticeRuleById(id)));
    }

    @ApiOperation("查询通知方式的可用数量和总数量")
    @GetMapping("/noticeRules/counts/{orgId}")
    public RespEntity<WarningMsgLicense> getNoticeWay(@PathVariable("orgId") Long orgId){
        return CtrlUtil.exe(r -> r.setVal(noticeRuleService.getNoticeWayByOrgId(orgId)));
    }

    @ApiOperation("根据条件查询通知规则设置列表（分页）")
    @PostMapping("/noticeRules/pages")
    public RespEntity<Page<NoticeRuleQueryParam>> getNoticesPageByCondition(@RequestBody Request<NoticeRuleQueryParam> request){
        return CtrlUtil.exe(r -> {
            Page<NoticeRuleQueryParam> targets = noticeRuleService.findNoticeRulePagesByContidion(QueryParameter.getInstance(request),request.getQuery());
            r.setVal(targets);
        });
    }

    @ApiOperation("保存通知规则")
    @PostMapping("/noticeRules")
    public RespEntity insertNoticeRules(@RequestBody NoticeRuleDto noticeRuleDto){
         return CtrlUtil.exe(r -> {
            NoticeRule noticeRule =  MapperUtil.copy(noticeRuleDto,NoticeRule.class);
             noticeRuleService.insert(noticeRule);
         });
    }

    @ApiOperation("更新通知规则")
    @PostMapping("/noticeRules/{id}")
    public RespEntity updateNoticeRules(@PathVariable("id") Long id,@RequestBody NoticeRuleDto noticeRuleDto){
        return CtrlUtil.exe(r -> {
            //根据id查询通知规则
            NoticeRule oldNoticeRule = noticeRuleService.getNoticeRuleById(id);
            MapperUtil.copy(noticeRuleDto,oldNoticeRule);
            oldNoticeRule.setNoticeWayList(noticeRuleDto.getNoticeWayList());
            oldNoticeRule.setNoticeUserList(noticeRuleDto.getNoticeUserList());
            noticeRuleService.update(oldNoticeRule);
        });
    }

    @ApiOperation("删除通知规则")
    @PostMapping("/noticeRules/{id}/delete")
    public RespEntity deleteNoticeRule(@PathVariable("id") Long id){
        return CtrlUtil.exe(r -> {
            //根据id查询通知规则
            NoticeRule noticeRule = noticeRuleService.getNoticeRuleById(id);
            noticeRuleService.deleteLogic(noticeRule);
        });
    }
}
