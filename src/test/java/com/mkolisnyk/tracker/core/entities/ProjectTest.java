package com.mkolisnyk.tracker.core.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectTest {
    private Project project = new Project() {
        {
            setId(1);
            setName("Name");
            setCode("TC");
        }
    };
    @Test
    public void testEqualsShouldBeTrueForTheSameObject() {
        Assertions.assertTrue(project.equals(project));
    }
    @Test
    public void testEqualsShouldBeTrueForObjectWithTheSameFieldValues() {
        Project project2 = new Project() {
            {
                setId(project.getId());
                setName(project.getName());
                setCode(project.getCode());
            }
        };
        Assertions.assertTrue(project.equals(project2));
        Assertions.assertTrue(project2.equals(project));
    }
    @Test
    public void testEqualsShouldBeFalseForProjectWithDifferentFieldValues() {
        Project project2 = new Project() {
            {
                setId(project.getId() + 1);
                setName(project.getName() + " ");
                setCode(project.getCode() + " ");
            }
        };
        Assertions.assertFalse(project.equals(project2));
        Assertions.assertFalse(project2.equals(project));

        project2 = new Project() {
            {
                setId(project.getId());
                setName(project.getName() + " ");
                setCode(project.getCode());
            }
        };
        Assertions.assertFalse(project.equals(project2));
        Assertions.assertFalse(project2.equals(project));

        project2 = new Project() {
            {
                setId(project.getId());
                setName(project.getName());
                setCode(project.getCode() + " ");
            }
        };
        Assertions.assertFalse(project.equals(project2));
        Assertions.assertFalse(project2.equals(project));
    }
    @Test
    public void testEqualsShouldBeFalseForDifferentType() {
        Assertions.assertFalse(project.equals(Integer.valueOf(1)));
    }
    @Test
    public void testHashCodeShouldBeTheSameForObjectsWithEqualFieldValues() {
        Project project2 = new Project() {
            {
                setId(project.getId());
                setName(project.getName());
                setCode(project.getCode());
            }
        };
        Assertions.assertEquals(project.hashCode(), project2.hashCode());
    }
    @Test
    public void testHashCodeShouldBeDifferentForObjectsWithDifferentFieldValues() {
        Project project2 = new Project() {
            {
                setId(project.getId() + 1);
                setName(project.getName() + " ");
                setCode(project.getCode() + " ");
            }
        };
        Assertions.assertNotEquals(project.hashCode(), project2.hashCode());
    }
}
