package res;



import java.util.HashMap;
import java.util.Map;

/**
 * @author kangkang
 * 统一的错误返回结构
 */
public class ResultDTO<T> {
    private String errCode;
    private String errMsg;
    private Boolean success = true;
    private T data;
    private Long total;
    private Map<String, String> map = new HashMap<>();
    private ResultDTO() {

    }

    public static <T> ResultDTO<T> buildSuccess(T t, Number total) {
        ResultDTO<T> dto = new ResultDTO<>();
        dto.setTotal(total.longValue());
        return dto;
    }
    public static <T> ResultDTO<T> buildSuccess(T t) {
        ResultDTO<T> dto = new ResultDTO<>();
        dto.setData(t);
        return dto;
    }

    public static <T> ResultDTO<T> buildFail(String errMsg, String errCode) {
        ResultDTO<T> dto = buildFail(errMsg);
        dto.setErrCode(errCode);
        dto.setSuccess(false);
        return dto;
    }

    public static <T> ResultDTO<T> buildFail(String errMsg) {
        ResultDTO<T> dto = new ResultDTO<>();
        dto.setErrMsg(errMsg);
        dto.setSuccess(false);
        return dto;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
