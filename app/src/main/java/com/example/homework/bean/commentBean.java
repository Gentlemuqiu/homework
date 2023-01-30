package com.example.homework.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class commentBean {
    @SerializedName("data")
    private DataDTO data;
    @SerializedName("errorCode")
    private Integer errorCode;
    @SerializedName("errorMsg")
    private String errorMsg;

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataDTO {
        @SerializedName("curPage")
        private Integer curPage;
        @SerializedName("datas")
        private List<DatasDTO> datas;
        @SerializedName("offset")
        private Integer offset;
        @SerializedName("over")
        private Boolean over;
        @SerializedName("pageCount")
        private Integer pageCount;
        @SerializedName("size")
        private Integer size;
        @SerializedName("total")
        private Integer total;

        public Integer getCurPage() {
            return curPage;
        }

        public void setCurPage(Integer curPage) {
            this.curPage = curPage;
        }

        public List<DatasDTO> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasDTO> datas) {
            this.datas = datas;
        }

        public Integer getOffset() {
            return offset;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }

        public Boolean getOver() {
            return over;
        }

        public void setOver(Boolean over) {
            this.over = over;
        }

        public Integer getPageCount() {
            return pageCount;
        }

        public void setPageCount(Integer pageCount) {
            this.pageCount = pageCount;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public static class DatasDTO {
            @SerializedName("anonymous")
            private Integer anonymous;
            @SerializedName("appendForContent")
            private Integer appendForContent;
            @SerializedName("articleId")
            private Integer articleId;
            @SerializedName("canEdit")
            private Boolean canEdit;
            @SerializedName("content")
            private String content;
            @SerializedName("contentMd")
            private String contentMd;
            @SerializedName("id")
            private Integer id;
            @SerializedName("niceDate")
            private String niceDate;
            @SerializedName("publishDate")
            private Long publishDate;
            @SerializedName("replyCommentId")
            private Integer replyCommentId;
            @SerializedName("replyComments")
            private List<ReplyCommentsDTO> replyComments;
            @SerializedName("rootCommentId")
            private Integer rootCommentId;
            @SerializedName("status")
            private Integer status;
            @SerializedName("toUserId")
            private Integer toUserId;
            @SerializedName("toUserName")
            private String toUserName;
            @SerializedName("userId")
            private Integer userId;
            @SerializedName("userName")
            private String userName;
            @SerializedName("zan")
            private Integer zan;

            public Integer getAnonymous() {
                return anonymous;
            }

            public void setAnonymous(Integer anonymous) {
                this.anonymous = anonymous;
            }

            public Integer getAppendForContent() {
                return appendForContent;
            }

            public void setAppendForContent(Integer appendForContent) {
                this.appendForContent = appendForContent;
            }

            public Integer getArticleId() {
                return articleId;
            }

            public void setArticleId(Integer articleId) {
                this.articleId = articleId;
            }

            public Boolean getCanEdit() {
                return canEdit;
            }

            public void setCanEdit(Boolean canEdit) {
                this.canEdit = canEdit;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getContentMd() {
                return contentMd;
            }

            public void setContentMd(String contentMd) {
                this.contentMd = contentMd;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public Long getPublishDate() {
                return publishDate;
            }

            public void setPublishDate(Long publishDate) {
                this.publishDate = publishDate;
            }

            public Integer getReplyCommentId() {
                return replyCommentId;
            }

            public void setReplyCommentId(Integer replyCommentId) {
                this.replyCommentId = replyCommentId;
            }

            public List<ReplyCommentsDTO> getReplyComments() {
                return replyComments;
            }

            public void setReplyComments(List<ReplyCommentsDTO> replyComments) {
                this.replyComments = replyComments;
            }

            public Integer getRootCommentId() {
                return rootCommentId;
            }

            public void setRootCommentId(Integer rootCommentId) {
                this.rootCommentId = rootCommentId;
            }

            public Integer getStatus() {
                return status;
            }

            public void setStatus(Integer status) {
                this.status = status;
            }

            public Integer getToUserId() {
                return toUserId;
            }

            public void setToUserId(Integer toUserId) {
                this.toUserId = toUserId;
            }

            public String getToUserName() {
                return toUserName;
            }

            public void setToUserName(String toUserName) {
                this.toUserName = toUserName;
            }

            public Integer getUserId() {
                return userId;
            }

            public void setUserId(Integer userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public Integer getZan() {
                return zan;
            }

            public void setZan(Integer zan) {
                this.zan = zan;
            }

            public static class ReplyCommentsDTO {
                @SerializedName("anonymous")
                private Integer anonymous;
                @SerializedName("appendForContent")
                private Integer appendForContent;
                @SerializedName("articleId")
                private Integer articleId;
                @SerializedName("canEdit")
                private Boolean canEdit;
                @SerializedName("content")
                private String content;
                @SerializedName("contentMd")
                private String contentMd;
                @SerializedName("id")
                private Integer id;
                @SerializedName("niceDate")
                private String niceDate;
                @SerializedName("publishDate")
                private Long publishDate;
                @SerializedName("replyCommentId")
                private Integer replyCommentId;
                @SerializedName("replyComments")
                private List<?> replyComments;
                @SerializedName("rootCommentId")
                private Integer rootCommentId;
                @SerializedName("status")
                private Integer status;
                @SerializedName("toUserId")
                private Integer toUserId;
                @SerializedName("toUserName")
                private String toUserName;
                @SerializedName("userId")
                private Integer userId;
                @SerializedName("userName")
                private String userName;
                @SerializedName("zan")
                private Integer zan;

                public Integer getAnonymous() {
                    return anonymous;
                }

                public void setAnonymous(Integer anonymous) {
                    this.anonymous = anonymous;
                }

                public Integer getAppendForContent() {
                    return appendForContent;
                }

                public void setAppendForContent(Integer appendForContent) {
                    this.appendForContent = appendForContent;
                }

                public Integer getArticleId() {
                    return articleId;
                }

                public void setArticleId(Integer articleId) {
                    this.articleId = articleId;
                }

                public Boolean getCanEdit() {
                    return canEdit;
                }

                public void setCanEdit(Boolean canEdit) {
                    this.canEdit = canEdit;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getContentMd() {
                    return contentMd;
                }

                public void setContentMd(String contentMd) {
                    this.contentMd = contentMd;
                }

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public String getNiceDate() {
                    return niceDate;
                }

                public void setNiceDate(String niceDate) {
                    this.niceDate = niceDate;
                }

                public Long getPublishDate() {
                    return publishDate;
                }

                public void setPublishDate(Long publishDate) {
                    this.publishDate = publishDate;
                }

                public Integer getReplyCommentId() {
                    return replyCommentId;
                }

                public void setReplyCommentId(Integer replyCommentId) {
                    this.replyCommentId = replyCommentId;
                }

                public List<?> getReplyComments() {
                    return replyComments;
                }

                public void setReplyComments(List<?> replyComments) {
                    this.replyComments = replyComments;
                }

                public Integer getRootCommentId() {
                    return rootCommentId;
                }

                public void setRootCommentId(Integer rootCommentId) {
                    this.rootCommentId = rootCommentId;
                }

                public Integer getStatus() {
                    return status;
                }

                public void setStatus(Integer status) {
                    this.status = status;
                }

                public Integer getToUserId() {
                    return toUserId;
                }

                public void setToUserId(Integer toUserId) {
                    this.toUserId = toUserId;
                }

                public String getToUserName() {
                    return toUserName;
                }

                public void setToUserName(String toUserName) {
                    this.toUserName = toUserName;
                }

                public Integer getUserId() {
                    return userId;
                }

                public void setUserId(Integer userId) {
                    this.userId = userId;
                }

                public String getUserName() {
                    return userName;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public Integer getZan() {
                    return zan;
                }

                public void setZan(Integer zan) {
                    this.zan = zan;
                }
            }
        }
    }
}