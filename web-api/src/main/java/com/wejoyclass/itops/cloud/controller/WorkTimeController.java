package com.wejoyclass.itops.cloud.controller;

import com.wejoyclass.core.util.CtrlUtil;
import com.wejoyclass.core.util.MapperUtil;
import com.wejoyclass.core.util.RespEntity;
import com.wejoyclass.itops.cloud.dto.WorkTimeDto;
import com.wejoyclass.itops.cloud.entity.WorkTime;
import com.wejoyclass.itops.cloud.service.WorkTimeService;
import com.wejoyclass.service.util.EntityUtil;
import com.wejoyclass.service.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "工作时间设置")
@RequestMapping("/work")
public class WorkTimeController {

   @Autowired
   private WorkTimeService workTimeService;

    @ApiOperation("获取默认设置工作时间")
    @GetMapping("/worktimes")
    public RespEntity<WorkTime> getWorkTime(){
        return CtrlUtil.exe(r -> r.setVal(workTimeService.getWorkTimeByOrgId(SecurityUtil.getTopOrgId())));
    }

    @ApiOperation("插入工作时间设置")
    @PostMapping("/workTimes")
    public RespEntity insertWorkTime(@RequestBody WorkTimeDto workTimeDto){
        return CtrlUtil.exe(r -> {
            WorkTime newWorkTime = MapperUtil.copy(workTimeDto,WorkTime.class);
            workTimeService.insert(newWorkTime);
        });
    }
    @ApiOperation("更新设置工作时间")
    @PostMapping("/worktimes/{id}")
    public RespEntity<WorkTime> updateWorkTime(@PathVariable("id") Long id, @RequestBody WorkTime opsWorkTime){
        return CtrlUtil.exe(r ->{
            //获取设置工作时间
            WorkTime oldOpsWorkTime = workTimeService.get(id);
            MapperUtil.copy(opsWorkTime,oldOpsWorkTime);
            EntityUtil.setUpdate(oldOpsWorkTime);
            workTimeService.update(oldOpsWorkTime);
        });
    }
}
