package org.example.compracarrito.controller;

import org.example.compracarrito.services.DatabaseExportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final DatabaseExportService exportService;

    public AdminController(DatabaseExportService exportService) {
        this.exportService = exportService;
    }

    @GetMapping
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportDatabase() {
        byte[] sql = exportService.exportDatabaseToSql();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=productos.sql")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(sql);
    }
}
