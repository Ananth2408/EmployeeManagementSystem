package com.ideas2it.employee.mapper;

import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper convert the modelDto details to model details and
 * model to modelDto details
 *
 * @Version 4.1 10-10-2022
 * @author  Ananth k.
 */
public class EmployeeMapper {

    /**
     * Coverts Employeedetails to EmployeeDtodetails
     *
     * @param Employee Details
     * @return EmployeeDto details
     */    
    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDto = null;
        List<AddressDTO> addressDto = new ArrayList<AddressDTO>();
        
            if (null != employee) {
                employeeDto = new EmployeeDTO();
                employeeDto.setId(employee.getId());
                employeeDto.setFirstName(employee.getFirstName());
                employeeDto.setLastName(employee.getLastName());
                employeeDto.setDateOfBirth(employee.getDateOfBirth());
                employeeDto.setPhoneNumber(employee.getPhoneNumber());
                employeeDto.setDateOfJoining(employee.getDateOfJoining());
                employeeDto.setEmail(employee.getEmail());
                employeeDto.setGender(employee.getGender());
                employeeDto.setSalary(employee.getSalary());
                employeeDto.setRole(employee.getRole());

                if (null != employee.getAddress()) {

                    for (Address address: employee.getAddress()) {
                         addressDto.add(toAddressDTO(address));
                    }
                    employeeDto.setAddress(addressDto);
               }
        }
        return employeeDto;
    }

    /**
     * Coverts EmployeeDtodetails to Employeedetails
     *
     * @param EmployeeDto Details
     * @return Employee details
     */  
    public static Employee toEmployee(EmployeeDTO employeeDto) {
        Employee employee = new Employee();
        List<Address> address = new ArrayList<Address>();

        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setDateOfJoining(employeeDto.getDateOfJoining());
        employee.setEmail(employeeDto.getEmail());
        employee.setGender(employeeDto.getGender());
        employee.setSalary(employeeDto.getSalary());
        employee.setRole(employeeDto.getRole());

        if (null != employeeDto.getAddress()) {

            for (AddressDTO addressDto: employeeDto.getAddress()) {
                address.add(toAddress(addressDto));
            }
        employee.setAddress(address);
        }
        return employee;
    }

   /**
     * Coverts Addressdetails to AddressDtodetails
     *
     * @param Address Details
     * @return AddressDto details
     */
    public static AddressDTO toAddressDTO(Address address) {
        AddressDTO addressDto = new AddressDTO();

        addressDto.setDoorNumber(address.getDoorNumber());
        addressDto.setStreet(address.getStreet());
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setPinCode(address.getPinCode());
        addressDto.setType(address.getType());
        return addressDto;
    }

    /**
     * Coverts AddressDtodetails to Addressdetails
     *
     * @param AddressDto Details
     * @return Address details
     */
    public static Address toAddress(AddressDTO addressDto) {
        Address address = new Address();

        address.setDoorNumber(addressDto.getDoorNumber());
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setPinCode(addressDto.getPinCode());
        address.setType(addressDto.getType());
        return address;
    }
}