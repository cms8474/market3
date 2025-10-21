package kr.co.team3.controller;

import kr.co.team3.admin_dto.VersionDTO;
import kr.co.team3.admin_service.VersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

// 강민철 2025-10-21 1214

@ControllerAdvice(annotations={Controller.class})
@RequiredArgsConstructor
public class GlobalInfoController {
    private final VersionService versionService;
    @ModelAttribute("version")
    public VersionDTO version() {
        return versionService.getLatestVersion().toDTO();
    }
}
