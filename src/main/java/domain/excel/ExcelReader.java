package domain.excel;

import domain.dto.DTO;

import java.nio.file.Path;

public interface ExcelReader
{
    DTO read( Path path );
}
