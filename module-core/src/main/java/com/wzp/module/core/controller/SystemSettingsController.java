package com.wzp.module.core.controller;

import cn.hutool.core.util.StrUtil;
import com.wzp.module.core.result.ResultDataModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description :
 * @Author : wzp
 * @Date : 2020/8/13
 */
@RestController
@RequestMapping("/base")
@Slf4j
public class SystemSettingsController {

    /**
     * 修改日志等级
     * @param levelStr
     * @param clazz
     * @return
     */
    @PostMapping("/log/level")
    public ResultDataModel chgLogLevel(@RequestParam String levelStr, @RequestParam(required = false) String clazz) {

        LoggerContext loggerContext = LoggerContext.getContext(false);

        LoggerConfig loggerConfig = StrUtil.isNotBlank(clazz) ? loggerContext.getConfiguration().getLoggerConfig(clazz)
                : loggerContext.getConfiguration().getRootLogger();

        log.debug("现有日志级别：" + loggerConfig.getLevel());
        Level level = Level.toLevel(levelStr);
        loggerConfig.setLevel(level);
        log.debug("调整后日志级别：" + loggerConfig.getLevel());
        loggerContext.updateLoggers();
        return ResultDataModel.handleSuccessResult("调整后日志级别：" + loggerConfig.getLevel());

    }
}
