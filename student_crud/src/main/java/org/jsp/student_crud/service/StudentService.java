package org.jsp.student_crud.service;

import java.util.ArrayList;
import java.util.List;

import org.jsp.student_crud.dao.StudentDao;
import org.jsp.student_crud.dto.Students;
import org.jsp.student_crud.exception.DataNotFoundException;
import org.jsp.student_crud.exception.DataShouldNotRepeatException;
import org.jsp.student_crud.helper.ResponseStrucrure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service // business logic is present
public class StudentService {
	@Autowired
	StudentDao dao;

	public ResponseStrucrure<Students> save(Students students) {
		Students student1 = dao.findByMobile(students.getMobile());// there is not
		Students student2 = dao.findByEmail(students.getEmail());
		if (student1 == null && student2 == null) {
			double percentage = (students.getEnglish() + students.getScience() + students.getMaths()) / 3.0;
			students.setPercentage(percentage);
			if (students.getEnglish() < 35 || students.getMaths() < 35 || students.getScience() < 35
					|| percentage < 35) {
				students.setResult("fail");
			} else {
				if (percentage >= 85) {
					students.setResult("distinction");
				} else if (percentage >= 60) {
					students.setResult("first class");

				} else {
					students.setResult("second class");
				}

			}
			ResponseStrucrure<Students> strucrure = new ResponseStrucrure<>();
			strucrure.setData(dao.save(students));
			strucrure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
			strucrure.setMessage("Data Saves Successfully ");
			return strucrure;

		} else {
			if (student1 == null) {
				throw new DataShouldNotRepeatException("Email Should Not be Repeated:" + students.getEmail());
			} else if (student2 == null) {
				throw new DataShouldNotRepeatException("Mobile Should Not be Repeated:" + students.getMobile());
			} else {
				throw new DataShouldNotRepeatException(
						"Email and Mobile Should Not be Repeated:" + students.getEmail() + " " + students.getMobile());
			}
		}

	}

	public ResponseStrucrure<List<Students>> saveAll(List<Students> students) {
		List<Students> list = new ArrayList<Students>();
		for (Students student : students) {
			Students student1 = dao.findByMobile(student.getMobile());
			Students student2 = dao.findByEmail(student.getEmail());

			if (student1 == null && student2 == null) {
				list.add(student);
			}
		}
		if (list.isEmpty()) {
			throw new DataShouldNotRepeatException("All Data Already Exists");
		} else {
			for (Students student : list) {
				double percentage = (student.getEnglish() + student.getScience() + student.getMaths()) / 3.0;
				student.setPercentage(percentage);
				if (student.getEnglish() < 35 || student.getMaths() < 35 || student.getScience() < 35
						|| percentage < 35) {
					student.setResult("fail");
				} else {
					if (percentage >= 85) {
						student.setResult("distinction");
					} else if (percentage >= 60) {
						student.setResult("first class");

					} else {
						student.setResult("second class");
					}
				}
			}
		}
		ResponseStrucrure<List<Students>> strucrure = new ResponseStrucrure<>();
		if (list.size() != students.size())
			strucrure.setMessage(
					list.size() + " " + "Data Saved ," + " " + (students.size() - list.size()) + "Data Not Saved");
		else {
			strucrure.setMessage("All Data Saved Successfully");
		}
		strucrure.setData(dao.saveAll(list));
		strucrure.setStatus(HttpStatus.CREATED.value());
		return strucrure;
	}

	public ResponseStrucrure<List<Students>> findAll() {
		List<Students> list = dao.findAll();
		if (list.isEmpty()) {
			throw new DataNotFoundException();
		} else {
			ResponseStrucrure<List<Students>> strucrure = new ResponseStrucrure<>();
			strucrure.setData(list);
			strucrure.setStatus(HttpStatus.FOUND.value());
			strucrure.setMessage("Data Found Successfully ");
			return strucrure;
		}
	}

	public ResponseStrucrure<Students> findById(int id) {
		ResponseStrucrure<Students> strucrure = new ResponseStrucrure<>();
		strucrure.setData(dao.findById(id));
		strucrure.setStatus(HttpStatus.FOUND.value());
		strucrure.setMessage("Data Found ");
		return strucrure;
	}

	public ResponseStrucrure<List<Students>> findByName(String name) {
		{
			List<Students> list = dao.findByName(name);
			if (list == null) {
				ResponseStrucrure<List<Students>> strucrure = new ResponseStrucrure<>();
				strucrure.setData(null);
				strucrure.setStatus(HttpStatus.FOUND.value());
				strucrure.setMessage("Data not Found success ");
				return strucrure;
			} else {
				ResponseStrucrure<List<Students>> strucrure = new ResponseStrucrure<>();
				strucrure.setData(list);
				strucrure.setStatus(HttpStatus.FOUND.value());
				strucrure.setMessage("Data Found ");
				return strucrure;
			}

		}
	}

	public ResponseStrucrure<Students> findByMobile(long mobile) {
		Students students = dao.findByMobile(mobile);
		if (students == null)
			throw new DataNotFoundException("Data Not Found with mobile " + mobile);

		ResponseStrucrure<Students> strucrure = new ResponseStrucrure<>();
		strucrure.setData(students);
		strucrure.setStatus(HttpStatus.FOUND.value());
		strucrure.setMessage("Data Found sucessfully.");
		return strucrure;

	}

	public ResponseStrucrure<Students> findByEmail(String email) {
		Students students = dao.findByEmail(email);
		if (students == null)
			throw new DataNotFoundException("Data Not Found with email " + email);
		else {
			ResponseStrucrure<Students> strucrure = new ResponseStrucrure<>();
			strucrure.setData(students);
			strucrure.setStatus(HttpStatus.FOUND.value());
			strucrure.setMessage("Data Found sucessfully.");
			return strucrure;
		}
	}

	public ResponseStrucrure<List<Students>> findByPercentageGreaterThan(double percentage) {
		List<Students> list = dao.findByPercentageGreaterThanEqual(percentage);
		if (list.isEmpty()) {
			throw new DataNotFoundException("No Students Record, whose Percentage is Greater Than " + percentage);
		} else {
			ResponseStrucrure<List<Students>> strucrure = new ResponseStrucrure<>();
			strucrure.setData(list);
			strucrure.setStatus(HttpStatus.FOUND.value());
			strucrure.setMessage("Data Found Success, " + list.size() + " Data is present");
			return strucrure;
		}
	}

	public ResponseStrucrure<List<Students>> findByPercentageLesserThan(double percentage) {
		List<Students> list = dao.findByPercentageLesserThanEqual(percentage);
		if (list.isEmpty()) {
			throw new DataNotFoundException("No Students Record, whose Percentage is Lesser Than " + percentage);
		} else {
			ResponseStrucrure<List<Students>> strucrure = new ResponseStrucrure<>();
			strucrure.setData(list);
			strucrure.setStatus(HttpStatus.FOUND.value());
			strucrure.setMessage("Data Found Success, " + list.size() + " Data is present");
			return strucrure;
		}
	}

	public ResponseStrucrure<List<Students>> findByResult(String result) {

		List<Students> list = dao.findByResult(result);
		if (list.isEmpty()) {
			throw new DataNotFoundException("No Students Record, whose Result is " + result);
		} else {
			ResponseStrucrure<List<Students>> strucrure = new ResponseStrucrure<>();
			strucrure.setData(list);
			strucrure.setStatus(HttpStatus.FOUND.value());
			strucrure.setMessage("Data Found Success, " + list.size() + " Data is present");
			return strucrure;
		}
	}

	public ResponseStrucrure<List<Students>> findByMathsEnglishGreater(int marks) {
		List<Students> list = dao.findByMathsEnglishGreater(marks);
		if (list.isEmpty()) {
			throw new DataNotFoundException("No Students Record, whose Result is " + marks);
		} else {
			ResponseStrucrure<List<Students>> strucrure = new ResponseStrucrure<>();
			strucrure.setData(list);
			strucrure.setStatus(HttpStatus.FOUND.value());
			strucrure.setMessage("Data Found Success, " + list.size() + " Data is present");
			return strucrure;
		}
	}

    public ResponseStrucrure<Students> delete(int id) {
		ResponseStrucrure<Students> strucrure = new ResponseStrucrure<Students>();
		strucrure.setMessage("Data Deleted with id, "+id);
		strucrure.setStatus(HttpStatus.GONE.value());
		strucrure.setData(dao.findById(id));
		dao.delete(dao.findById(id));
		return strucrure;
    }

	public ResponseStrucrure<Students> update(Students students) {
		dao.findById(students.getId());
			double percentage = (students.getEnglish() + students.getScience() + students.getMaths()) / 3.0;
			students.setPercentage(percentage);
			if (students.getEnglish() < 35 || students.getMaths() < 35 || students.getScience() < 35
					|| percentage < 35) {
				students.setResult("fail");
			} else {
				if (percentage >= 85) {
					students.setResult("distinction");
				} else if (percentage >= 60) {
					students.setResult("first class");

				} else {
					students.setResult("second class");
				}

			}
			ResponseStrucrure<Students> strucrure = new ResponseStrucrure<>();
			strucrure.setData(dao.update(students));
			strucrure.setStatus(HttpStatus.OK.value());
			strucrure.setMessage("Data Updated Successfully ");
			return strucrure;

	}

}
