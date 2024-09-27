package com.project.DatasourceProject.config.db;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum DatasourceType {
    PRIMARY("primary"),
    SECONDARY("secondary"),
    TERITIARY("teritiary");

  private  final String name;
}
