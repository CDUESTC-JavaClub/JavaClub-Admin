package club.cduestc.admin.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response {
    int status;
    Object data;
}
