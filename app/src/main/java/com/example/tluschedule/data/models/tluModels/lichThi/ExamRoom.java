package com.example.tluschedule.data.models.tluModels.lichThi;

import com.example.tluschedule.data.models.JsonModelBase;
import com.example.tluschedule.data.models.tluModels.studentCourse.Room;

public class ExamRoom extends JsonModelBase {
    private long id;
    private String roomCode;
    private long duration;
    private long examDate;
    private String examDateString;
    private long numberExpectedStudent;
    private long numberStudent;
    private long numberStudentAddToBag;
    private boolean isAddedToTestBag;
    private boolean isAddedFullToTestBag;
    private boolean isAbleToCreateBag;
    private String semesterName;
    private String courseYearName;
    private String registerPeriodName;
    private ExamHour examHour;
    private Room room;

    public long getId() {
        return id;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public long getDuration() {
        return duration;
    }

    public long getExamDate() {
        return examDate;
    }

    public String getExamDateString() {
        return examDateString;
    }

    public long getNumberExpectedStudent() {
        return numberExpectedStudent;
    }

    public long getNumberStudent() {
        return numberStudent;
    }

    public long getNumberStudentAddToBag() {
        return numberStudentAddToBag;
    }

    public boolean isAddedToTestBag() {
        return isAddedToTestBag;
    }

    public boolean isAddedFullToTestBag() {
        return isAddedFullToTestBag;
    }

    public boolean isAbleToCreateBag() {
        return isAbleToCreateBag;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public String getCourseYearName() {
        return courseYearName;
    }

    public String getRegisterPeriodName() {
        return registerPeriodName;
    }

    public ExamHour getExamHour() {
        return examHour;
    }

    public Room getRoom() {
        return room;
    }
}
