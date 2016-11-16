package com.example.chy.challenge.Models;

import java.util.List;

/**
 * Created by 77588 on 2016/11/15.
 */

public class BlackListInfo {

    /**
     * status : success
     * data : [{"id":"3","userid":"301","blackid":"301","create_time":"1479101985","status":"1","reason":"拉黑","type":"2","blacks":{"userid":"301","realname":"lala","usertype":"3","phone":"18363867129","password":"###7b156c659264be1b6892219fb25e5b23","sex":"0","email":"","qq":"","weixin":"","photo":"avatar20161103112919301.png","devicestate":"1","city":"广州","weibo":"","work_life":"1年以内","company":"校酷","myjob":"iOS","resumes_id":"2","work_property":"全职","address":"上海市-上海市","position_type":"软件工程师","categories":"无线产品","wantsalary":"1-5","jobstate":"离职","advantage":"4~5万","education":[{"userid":"301","school":"陆大","major":"数学","degree":"硕士","time":"2010年-2010年","experience":"撒发生的","create_time":"1476933396"},{"userid":"301","school":"Luring ","major":"Sdjfaakls","degree":"大专","time":"2011年-2011年","experience":"Sjaklfjadsklfjlkfjoweirufjc","create_time":"1476947346"},{"userid":"301","school":"Xiaoxue","major":"Shy ante","degree":"大专","time":"2010年-2010年","experience":"看撒的肌肤快乐的撒娇份理解为普及法律方法是大风 ","create_time":"1476952145"},{"userid":"301","school":"刻苦","major":"快咯","degree":"硕士","time":"2019年-2019年","experience":"哈饼","create_time":"1478144040"},{"userid":"301","school":"刻苦","major":"快咯","degree":"选择学历","time":"请选择就读时间段","experience":"哈饼","create_time":"1479108833"},{"userid":"301","school":"","major":"","degree":"硕士","time":"2010年-2015年","experience":"","create_time":"1479109387"},{"userid":"301","school":"","major":"","degree":"硕士","time":"2010年-2014年","experience":"","create_time":"1479109409"}],"work":[{"userid":"301","company_name":"校酷","company_industry":"交互设计","jobtype":"技术专员/助理","skill":"电子商务-智能硬件-用户研究","work_period":"2010年.1月-2010年.1月","create_time":"1478143981","content":"4~5万"}],"project":[{"userid":"301","project_name":"可口可乐了","start_time":"2011年.1月","end_time":"2012年.1月","description_project":"项目描述湖酒店睡了快放假啊圣诞快乐附","create_time":"1478143911"}]}}]
     */

    private String status;
    /**
     * id : 3
     * userid : 301
     * blackid : 301
     * create_time : 1479101985
     * status : 1
     * reason : 拉黑
     * type : 2
     * blacks : {"userid":"301","realname":"lala","usertype":"3","phone":"18363867129","password":"###7b156c659264be1b6892219fb25e5b23","sex":"0","email":"","qq":"","weixin":"","photo":"avatar20161103112919301.png","devicestate":"1","city":"广州","weibo":"","work_life":"1年以内","company":"校酷","myjob":"iOS","resumes_id":"2","work_property":"全职","address":"上海市-上海市","position_type":"软件工程师","categories":"无线产品","wantsalary":"1-5","jobstate":"离职","advantage":"4~5万","education":[{"userid":"301","school":"陆大","major":"数学","degree":"硕士","time":"2010年-2010年","experience":"撒发生的","create_time":"1476933396"},{"userid":"301","school":"Luring ","major":"Sdjfaakls","degree":"大专","time":"2011年-2011年","experience":"Sjaklfjadsklfjlkfjoweirufjc","create_time":"1476947346"},{"userid":"301","school":"Xiaoxue","major":"Shy ante","degree":"大专","time":"2010年-2010年","experience":"看撒的肌肤快乐的撒娇份理解为普及法律方法是大风 ","create_time":"1476952145"},{"userid":"301","school":"刻苦","major":"快咯","degree":"硕士","time":"2019年-2019年","experience":"哈饼","create_time":"1478144040"},{"userid":"301","school":"刻苦","major":"快咯","degree":"选择学历","time":"请选择就读时间段","experience":"哈饼","create_time":"1479108833"},{"userid":"301","school":"","major":"","degree":"硕士","time":"2010年-2015年","experience":"","create_time":"1479109387"},{"userid":"301","school":"","major":"","degree":"硕士","time":"2010年-2014年","experience":"","create_time":"1479109409"}],"work":[{"userid":"301","company_name":"校酷","company_industry":"交互设计","jobtype":"技术专员/助理","skill":"电子商务-智能硬件-用户研究","work_period":"2010年.1月-2010年.1月","create_time":"1478143981","content":"4~5万"}],"project":[{"userid":"301","project_name":"可口可乐了","start_time":"2011年.1月","end_time":"2012年.1月","description_project":"项目描述湖酒店睡了快放假啊圣诞快乐附","create_time":"1478143911"}]}
     */

    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String userid;
        private String blackid;
        private String create_time;
        private String status;
        private String reason;
        private String type;
        /**
         * userid : 301
         * realname : lala
         * usertype : 3
         * phone : 18363867129
         * password : ###7b156c659264be1b6892219fb25e5b23
         * sex : 0
         * email :
         * qq :
         * weixin :
         * photo : avatar20161103112919301.png
         * devicestate : 1
         * city : 广州
         * weibo :
         * work_life : 1年以内
         * company : 校酷
         * myjob : iOS
         * resumes_id : 2
         * work_property : 全职
         * address : 上海市-上海市
         * position_type : 软件工程师
         * categories : 无线产品
         * wantsalary : 1-5
         * jobstate : 离职
         * advantage : 4~5万
         * education : [{"userid":"301","school":"陆大","major":"数学","degree":"硕士","time":"2010年-2010年","experience":"撒发生的","create_time":"1476933396"},{"userid":"301","school":"Luring ","major":"Sdjfaakls","degree":"大专","time":"2011年-2011年","experience":"Sjaklfjadsklfjlkfjoweirufjc","create_time":"1476947346"},{"userid":"301","school":"Xiaoxue","major":"Shy ante","degree":"大专","time":"2010年-2010年","experience":"看撒的肌肤快乐的撒娇份理解为普及法律方法是大风 ","create_time":"1476952145"},{"userid":"301","school":"刻苦","major":"快咯","degree":"硕士","time":"2019年-2019年","experience":"哈饼","create_time":"1478144040"},{"userid":"301","school":"刻苦","major":"快咯","degree":"选择学历","time":"请选择就读时间段","experience":"哈饼","create_time":"1479108833"},{"userid":"301","school":"","major":"","degree":"硕士","time":"2010年-2015年","experience":"","create_time":"1479109387"},{"userid":"301","school":"","major":"","degree":"硕士","time":"2010年-2014年","experience":"","create_time":"1479109409"}]
         * work : [{"userid":"301","company_name":"校酷","company_industry":"交互设计","jobtype":"技术专员/助理","skill":"电子商务-智能硬件-用户研究","work_period":"2010年.1月-2010年.1月","create_time":"1478143981","content":"4~5万"}]
         * project : [{"userid":"301","project_name":"可口可乐了","start_time":"2011年.1月","end_time":"2012年.1月","description_project":"项目描述湖酒店睡了快放假啊圣诞快乐附","create_time":"1478143911"}]
         */

        private BlacksBean blacks;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getBlackid() {
            return blackid;
        }

        public void setBlackid(String blackid) {
            this.blackid = blackid;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public BlacksBean getBlacks() {
            return blacks;
        }

        public void setBlacks(BlacksBean blacks) {
            this.blacks = blacks;
        }

        public static class BlacksBean {
            private String userid;
            private String realname;
            private String usertype;
            private String phone;
            private String password;
            private String sex;
            private String email;
            private String qq;
            private String weixin;
            private String photo;
            private String devicestate;
            private String city;
            private String weibo;
            private String work_life;
            private String company;
            private String myjob;
            private String resumes_id;
            private String work_property;
            private String address;
            private String position_type;
            private String categories;
            private String wantsalary;
            private String jobstate;
            private String advantage;
            /**
             * userid : 301
             * school : 陆大
             * major : 数学
             * degree : 硕士
             * time : 2010年-2010年
             * experience : 撒发生的
             * create_time : 1476933396
             */

            private List<EducationBean> education;
            /**
             * userid : 301
             * company_name : 校酷
             * company_industry : 交互设计
             * jobtype : 技术专员/助理
             * skill : 电子商务-智能硬件-用户研究
             * work_period : 2010年.1月-2010年.1月
             * create_time : 1478143981
             * content : 4~5万
             */

            private List<WorkBean> work;
            /**
             * userid : 301
             * project_name : 可口可乐了
             * start_time : 2011年.1月
             * end_time : 2012年.1月
             * description_project : 项目描述湖酒店睡了快放假啊圣诞快乐附
             * create_time : 1478143911
             */

            private List<ProjectBean> project;

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getUsertype() {
                return usertype;
            }

            public void setUsertype(String usertype) {
                this.usertype = usertype;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            public String getWeixin() {
                return weixin;
            }

            public void setWeixin(String weixin) {
                this.weixin = weixin;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getDevicestate() {
                return devicestate;
            }

            public void setDevicestate(String devicestate) {
                this.devicestate = devicestate;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getWeibo() {
                return weibo;
            }

            public void setWeibo(String weibo) {
                this.weibo = weibo;
            }

            public String getWork_life() {
                return work_life;
            }

            public void setWork_life(String work_life) {
                this.work_life = work_life;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getMyjob() {
                return myjob;
            }

            public void setMyjob(String myjob) {
                this.myjob = myjob;
            }

            public String getResumes_id() {
                return resumes_id;
            }

            public void setResumes_id(String resumes_id) {
                this.resumes_id = resumes_id;
            }

            public String getWork_property() {
                return work_property;
            }

            public void setWork_property(String work_property) {
                this.work_property = work_property;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getPosition_type() {
                return position_type;
            }

            public void setPosition_type(String position_type) {
                this.position_type = position_type;
            }

            public String getCategories() {
                return categories;
            }

            public void setCategories(String categories) {
                this.categories = categories;
            }

            public String getWantsalary() {
                return wantsalary;
            }

            public void setWantsalary(String wantsalary) {
                this.wantsalary = wantsalary;
            }

            public String getJobstate() {
                return jobstate;
            }

            public void setJobstate(String jobstate) {
                this.jobstate = jobstate;
            }

            public String getAdvantage() {
                return advantage;
            }

            public void setAdvantage(String advantage) {
                this.advantage = advantage;
            }

            public List<EducationBean> getEducation() {
                return education;
            }

            public void setEducation(List<EducationBean> education) {
                this.education = education;
            }

            public List<WorkBean> getWork() {
                return work;
            }

            public void setWork(List<WorkBean> work) {
                this.work = work;
            }

            public List<ProjectBean> getProject() {
                return project;
            }

            public void setProject(List<ProjectBean> project) {
                this.project = project;
            }

            public static class EducationBean {
                private String userid;
                private String school;
                private String major;
                private String degree;
                private String time;
                private String experience;
                private String create_time;

                public String getUserid() {
                    return userid;
                }

                public void setUserid(String userid) {
                    this.userid = userid;
                }

                public String getSchool() {
                    return school;
                }

                public void setSchool(String school) {
                    this.school = school;
                }

                public String getMajor() {
                    return major;
                }

                public void setMajor(String major) {
                    this.major = major;
                }

                public String getDegree() {
                    return degree;
                }

                public void setDegree(String degree) {
                    this.degree = degree;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getExperience() {
                    return experience;
                }

                public void setExperience(String experience) {
                    this.experience = experience;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }
            }

            public static class WorkBean {
                private String userid;
                private String company_name;
                private String company_industry;
                private String jobtype;
                private String skill;
                private String work_period;
                private String create_time;
                private String content;

                public String getUserid() {
                    return userid;
                }

                public void setUserid(String userid) {
                    this.userid = userid;
                }

                public String getCompany_name() {
                    return company_name;
                }

                public void setCompany_name(String company_name) {
                    this.company_name = company_name;
                }

                public String getCompany_industry() {
                    return company_industry;
                }

                public void setCompany_industry(String company_industry) {
                    this.company_industry = company_industry;
                }

                public String getJobtype() {
                    return jobtype;
                }

                public void setJobtype(String jobtype) {
                    this.jobtype = jobtype;
                }

                public String getSkill() {
                    return skill;
                }

                public void setSkill(String skill) {
                    this.skill = skill;
                }

                public String getWork_period() {
                    return work_period;
                }

                public void setWork_period(String work_period) {
                    this.work_period = work_period;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }
            }

            public static class ProjectBean {
                private String userid;
                private String project_name;
                private String start_time;
                private String end_time;
                private String description_project;
                private String create_time;

                public String getUserid() {
                    return userid;
                }

                public void setUserid(String userid) {
                    this.userid = userid;
                }

                public String getProject_name() {
                    return project_name;
                }

                public void setProject_name(String project_name) {
                    this.project_name = project_name;
                }

                public String getStart_time() {
                    return start_time;
                }

                public void setStart_time(String start_time) {
                    this.start_time = start_time;
                }

                public String getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(String end_time) {
                    this.end_time = end_time;
                }

                public String getDescription_project() {
                    return description_project;
                }

                public void setDescription_project(String description_project) {
                    this.description_project = description_project;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }
            }
        }
    }
}
