package com.blog.module.business.controller;

import com.blog.module.business.domain.LeaveWord;
import com.blog.module.business.domain.User;
import com.blog.module.business.domain.bo.LeaveWordBO;
import com.blog.module.business.service.LeaveWordService;
import com.blog.page.dto.PageResultDTO;
import com.blog.page.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 功能描述：
 *
 * @author RenShiWei
 * Date: 2020/4/11 11:09
 **/
@RestController
@RequestMapping("/api/leave-word")
@Api(tags = "留言管理模块")
public class LeaveWordController {
    @Autowired
    private LeaveWordService leaveWordService;

    @ApiOperation(value = "新增留言信息",notes="新增一条留言信息; \n author：JQJ")
    @PostMapping
    public ResponseEntity<Void> saveLeaveWord (
            @ApiParam(name = "leaveWord", value = "新增的留言信息", required = true)
            @Validated User user, LeaveWord leaveWord) {
        this.leaveWordService.saveLeaveWord(user,leaveWord);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除单个留言信息",notes="根据留言id删除对应留言信息; \n author：JQJ")
    @DeleteMapping("{leaveWordId}")
    public ResponseEntity<Void> deleteLeaveWord (
            @ApiParam(name = "leaveWordId", value = "要删除留言id", required = true) @PathVariable("leaveWordId") Long leaveWordId ){
        this.leaveWordService.deleteLeaveWord(leaveWordId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "批量删除留言信息",notes="根据留言id集合批量删除留言信息; \n author：JQJ")
    @PostMapping("/ids")
    public ResponseEntity<Void> deleteLeaveWords (
            @ApiParam(name = "leaveWordIds", value = "留言id集合",required = true)
            @RequestBody List<Long> leaveWordIds ){
        this.leaveWordService.deleteLeaveWords(leaveWordIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @ApiOperation(value = "修改留言信息",notes="根据前台传入的信息进行修改对应留言信息; \n author：JQJ")
    @PutMapping
    public ResponseEntity<String> updateLeaveWord (
            @ApiParam(name = "leaveWord", value = "修改的留言信息", required = true)
            @Validated(LeaveWord.UpdateGroup.class) LeaveWord leaveWord ){
        this.leaveWordService.updateLeaveWord(leaveWord);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "查询所有的留言信息",notes="根据分页排序条件查询留言信息; \n author：JQJ")
    @GetMapping("/all")
    public ResponseEntity<PageResultDTO<LeaveWordBO>> queryLeaveWordAll (
            @ApiParam(name = "pageVo", value = "分页信息") PageVO pageVO ){
        PageResultDTO<LeaveWordBO> leaveWords = this.leaveWordService.queryLeaveWordAll(pageVO);
        return ResponseEntity.ok(leaveWords);
    }
}

