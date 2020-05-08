package top.fredyblog.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import top.fredyblog.blog.model.dto.RestResult;
import top.fredyblog.blog.model.entity.Type;
import top.fredyblog.blog.model.entity.TypeExample;
import top.fredyblog.blog.service.TypeService;
import top.fredyblog.blog.utils.ResultGenerator;

import javax.annotation.Resource;
import javax.validation.Valid;

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
     * 类型分页查询
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
     * 类型单个查询
     * @param id
     * @return
     */
    @ApiOperation("类型单个查询")
    @GetMapping("/type/{id}")
    public RestResult getType(@PathVariable Integer id){
        Type type = typeService.findOne(id);
        return ResultGenerator.getSuccessResult(type);
    }

    /**
     * 提交类型信息
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
            StringBuilder errorMessage = new StringBuilder("");
            for (FieldError fieldError : result.getFieldErrors()) {
                errorMessage.append(fieldError.getDefaultMessage() + ",");
            }
            return ResultGenerator.getFailResult(errorMessage.deleteCharAt(errorMessage.length()-1).toString());
        }
        typeService.saveType(type);
        return ResultGenerator.getSuccessResult();
    }

    /**
     * 更新类型信息
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
     * 删除类型信息
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
