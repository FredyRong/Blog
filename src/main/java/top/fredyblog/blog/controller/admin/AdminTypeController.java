package top.fredyblog.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import top.fredyblog.blog.exception.CustomizeErrorCode;
import top.fredyblog.blog.exception.CustomizeException;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.model.entity.Type;
import top.fredyblog.blog.model.entity.TypeExample;
import top.fredyblog.blog.service.TypeService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员：类型管理
 * @author Fredy
 * @date 2020/5/7 20:14
 */
@Api("管理员标签管理模块")
@RestController
@RequestMapping("/admin")
@Log4j2
public class AdminTypeController {
    @Resource
    private TypeService typeService;

    /**
     * 功能描述：类型分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("类型分页查询")
    @GetMapping("/types")
    public RestResult types(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize){
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andDelFlagEqualTo(false);
        typeExample.setOrderByClause("create_time");
        PageInfo<Type> page = typeService.page(typeExample, pageNum, pageSize);
        return ResultGenerator.getSuccessResult(page);
    }

    /**
     * 功能描述：类型单个查询
     * @param id
     * @return
     */
    @ApiOperation("类型单个查询")
    @GetMapping("/type/{id}")
    public RestResult getType(@PathVariable Integer id){
        Type type = typeService.findOne(id);
        if (type == null) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_NOT_FOUND);
        }
        return ResultGenerator.getSuccessResult(type);
    }

    /**
     * 功能描述：提交类型信息
     * @param type
     * @param result
     * @return
     */
    @ApiOperation("提交类型信息")
    @PostMapping("/type")
    public RestResult postType(@Valid Type type, BindingResult result){
        Type typeByName = typeService.findTypeByName(type.getTypeName());
        if (typeByName != null) {
            return ResultGenerator.getFailResult("不能添加重复类型");
        }
        if(result.hasErrors()){
            List<String> errorMessage = new ArrayList<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                errorMessage.add(fieldError.getDefaultMessage() + ",");
            }
            return ResultGenerator.getFailResult("validation error", errorMessage);
        }
        typeService.saveType(type);
        return ResultGenerator.getSuccessResult();
    }

    /**
     * 功能描述：更新类型信息
     * @param id
     * @param type
     * @return
     */
    @ApiOperation("更新类型信息")
    @PutMapping("/type/{id}")
    public RestResult postTag(@PathVariable Integer id, Type type){
        Type type1 = typeService.findOne(id);
        typeService.saveType(type);
        return ResultGenerator.getSuccessResult();
    }

    /**
     * 功能描述：删除类型信息
     * @param id
     * @return
     */
    @ApiOperation("删除类型信息")
    @DeleteMapping("/type/{id}")
    public RestResult deleteTag(@PathVariable Integer id){
        typeService.deleteType(id);
        return ResultGenerator.getSuccessResult();
    }
}
