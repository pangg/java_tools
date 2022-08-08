package com.xxx.bean2map.pojo;

import lombok.Data;
import java.util.Date;

@Data
public class MockObject extends MockParent{
    private Integer aInteger;
    private Long aLong;
    private Double aDouble;
    private Date aDate;
}
