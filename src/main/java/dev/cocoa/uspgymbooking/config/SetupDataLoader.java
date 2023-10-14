package dev.cocoa.uspgymbooking.config;

import dev.cocoa.uspgymbooking.authentication.privilege.Privilege;
import dev.cocoa.uspgymbooking.authentication.privilege.PrivilegeRepository;
import dev.cocoa.uspgymbooking.authentication.role.Role;
import dev.cocoa.uspgymbooking.authentication.role.RoleRepository;
import dev.cocoa.uspgymbooking.facility.Facility;
import dev.cocoa.uspgymbooking.facility.FacilityRepository;
import dev.cocoa.uspgymbooking.facility.facilitytype.FacilityType;
import dev.cocoa.uspgymbooking.facility.facilitytype.FacilityTypeRepository;
import dev.cocoa.uspgymbooking.user.User;
import dev.cocoa.uspgymbooking.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PrivilegeRepository privilegeRepository;

    private final FacilityTypeRepository facilityTypeRepository;

    private final FacilityRepository facilityRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        /*Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        //Create Admin User
        User user = new User();
        user.setFirstName("Admin");
        user.setLastName("Admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setEmail("admin@admin.com");
        user.setRoles(Arrays.asList(adminRole));
        user.setEnabled(true);
        userRepository.save(user);

        //Creating Outdoor field type and Soccer Field
        FacilityType facilityType = new FacilityType();
        facilityType.setName("Outdoor Field");
        facilityType.setRate(20);
        facilityTypeRepository.save(facilityType);*/

        alreadySetup = true;
    }

    @Transactional
    public Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    public Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
