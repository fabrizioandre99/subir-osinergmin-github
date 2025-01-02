package pe.gob.osinergmin.prie.admin.backend.Util;

import java.io.Serializable;

public class ResponseEntity<T> implements Serializable {
    private Integer statusCodeValue;
    private String msg;
    private T datas;


    public static <T> ResponseEntity<T> succeed(T model, String msg) {
        return of(CodeEnum.SUCCESS.getStatusCodeValue(), msg, model);
    }

    public static <T> ResponseEntity<T> succeed(T model) {
        return of(CodeEnum.SUCCESS.getStatusCodeValue(), "", model);
    }

    public static <T> ResponseEntity<T> of(Integer statusCodeValue, String msg, T datas) {
        return new ResponseEntity(statusCodeValue, msg, datas);
    }

    public static <T> ResponseEntity<T> failed(String msg) {
        return of(CodeEnum.ERROR.getStatusCodeValue(), msg, null);
    }

    public static <T> ResponseEntity<T> failed(T model, String msg) {
        return of(CodeEnum.ERROR.getStatusCodeValue(), msg, model);
    }

    public static <T> ResponseEntity<T> succeedWith(T data, Integer status, String msg) {
        return new ResponseEntity(status, msg, data);
    }

    public static <T> ResponseEntity<T> failedWith(T data, Integer status, String msg) {
        return new ResponseEntity(status, msg, data);
    }





    public Integer getStatusCodeValue() {
        return this.statusCodeValue;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getDatas() {
        return this.datas;
    }

    public void setStatusCodeValue(final Integer statusCodeValue) {
        this.statusCodeValue = statusCodeValue;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public void setDatas(final T datas) {
        this.datas = datas;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ResponseEntity)) {
            return false;
        } else {
            ResponseEntity<?> other = (ResponseEntity)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$statusCodeValue = this.getStatusCodeValue();
                    Object other$statusCodeValue = other.getStatusCodeValue();
                    if (this$statusCodeValue == null) {
                        if (other$statusCodeValue == null) {
                            break label47;
                        }
                    } else if (this$statusCodeValue.equals(other$statusCodeValue)) {
                        break label47;
                    }

                    return false;
                }

                Object this$msg = this.getMsg();
                Object other$msg = other.getMsg();
                if (this$msg == null) {
                    if (other$msg != null) {
                        return false;
                    }
                } else if (!this$msg.equals(other$msg)) {
                    return false;
                }

                Object this$datas = this.getDatas();
                Object other$datas = other.getDatas();
                if (this$datas == null) {
                    if (other$datas != null) {
                        return false;
                    }
                } else if (!this$datas.equals(other$datas)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ResponseEntity;
    }

    public int hashCode() {
        int result = 1;
        Object $statusCodeValue = this.getStatusCodeValue();
        result = result * 59 + ($statusCodeValue == null ? 43 : $statusCodeValue.hashCode());
        Object $msg = this.getMsg();
        result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
        Object $datas = this.getDatas();
        result = result * 59 + ($datas == null ? 43 : $datas.hashCode());
        return result;
    }

    public String toString() {
        return "ResponseEntity(statusCodeValue=" + this.getStatusCodeValue() + ", msg=" + this.getMsg() + ", datas=" + this.getDatas() + ")";
    }

    public ResponseEntity() {
    }

    public ResponseEntity(final Integer statusCodeValue, final String msg, final T datas) {
        this.statusCodeValue = statusCodeValue;
        this.msg = msg;
        this.datas = datas;
    }
}
