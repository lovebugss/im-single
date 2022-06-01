package com.itrjp.im.result;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/5/31 22:42
 */
public class Result<T> {

    private final int code;
    private final String message;
    private final T data;


    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result<Void> success() {
        return success(200, "success", null);
    }

    public static Result<Void> success(int code, String message) {
        return success(code, message, null);
    }

    public static <T> Result<T> success(int code, String message, T data) {
        return new Result<>(code, message, data);
    }

    public static Result<Void> error() {
        return error(-1, "error");
    }

    public static Result<Void> error(int code, String message) {
        return new Result<>(code, message, null);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
