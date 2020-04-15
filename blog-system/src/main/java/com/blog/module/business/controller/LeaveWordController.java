package com.blog.module.business.controller;

import com.blog.module.business.domain.LeaveWord;
import com.blog.module.business.domain.Visitor;
import com.blog.module.business.domain.bo.LeaveWordBO;
import com.blog.module.business.service.LeaveWordService;
import com.blog.page.dto.PageResultDTO;
import com.blog.page.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
    @ApiParam(name = "leaveWord", value = "新增的留言信息", required = true)
    @PostMapping
    public ResponseEntity<Void> saveLeaveWord (@Validated Visitor visitor, LeaveWord leaveWord) {
        this.leaveWordService.saveLeaveWord(visitor,leaveWord);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除单个留言信息",notes="根据留言id删除对应留言信息; \n author：JQJ")
    @ApiImplicitParam(name = "leaveWordId", value = "留言", required = true)
    @DeleteMapping("{leaveWordId}")
    public ResponseEntity<Void> deleteLeaveWord (@Valid @PathVariable("leaveWordId") Long leaveWordId ){
        this.leaveWordService.deleteLeaveWord(leaveWordId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "批量删除留言信息",notes="根据留言id集合批量删除留言信息; \n author：JQJ")
    @ApiImplicitParam(name = "leaveWordIds", value = "留言id集合", required = true)
    @DeleteMapping("/ids")
    public ResponseEntity<Void> deleteLeaveWords (@Valid @PathVariable("leaveWordIds") List<Long> leaveWordIds ){
        this.leaveWordService.deleteLeaveWords(leaveWordIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @ApiOperation(value = "修改留言信息",notes="根据前台传入的信息进行修改对应留言信息; \n author：JQJ")
    @ApiParam(name = "leaveWord", value = "修改的留言信息", required = true)
    @PutMapping
    public ResponseEntity<String> updateLeaveWord (@Validated(LeaveWord.UpdateGroup.class) LeaveWord leaveWord ){
        this.leaveWordService.updateLeaveWord(leaveWord);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @ApiOperation(value = "查询一条留言信息",notes="根据留言id查询对应留言信息; \n author：JQJ")
//    @ApiImplicitParam(name = "LeaveWordId", value = "留言id", required = true)
//    @GetMapping("{leaveWordId}")
//    public ResponseEntity<LeaveWord> queryLeaveWordById (@Valid @PathVariable("leaveWordId") Long leaveWordId ){
//        LeaveWord leaveWord = this.leaveWordService.queryLeaveWordById(leaveWordId);
//        return ResponseEntity.ok(leaveWord);
//    }

    @ApiOperation(value = "查询所有的留言信息",notes="根据分页排序条件查询留言信息; \n author：JQJ")
    @ApiParam(name = "pageVo", value = "分页信息")
    @GetMapping("/all")
    public ResponseEntity<PageResultDTO<LeaveWordBO>> queryLeaveWordAll (PageVO pageVO ){
        PageResultDTO<LeaveWordBO> leaveWords = this.leaveWordService.queryLeaveWordAll(pageVO);
        return ResponseEntity.ok(leaveWords);
    }

//    @ApiOperation(value = "查询所有符合状态的留言信息",notes="根据分页排序，和状态条件查询留言信息; \n author：JQJ")
//    @GetMapping("/{state}")
//    public ResponseEntity<PageResultDTO<LeaveWord>> queryLeaveWordsByState(
//            @ApiParam(name = "replyContent", value = "留言状态") @PathVariable("state") String replyContent,
//            @ApiParam(name = "pageVo", value = "分页信息") PageVO pageVO){
//        PageResultDTO<LeaveWord> leaveWords = (PageResultDTO<LeaveWord>) this.leaveWordService.queryLeaveWordsByState(replyContent,pageVO);
//        return ResponseEntity.ok(leaveWords);
//    }

}

