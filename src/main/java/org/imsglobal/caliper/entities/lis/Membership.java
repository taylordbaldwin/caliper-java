package org.imsglobal.caliper.entities.lis;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.validators.EntityValidator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * A Caliper Membership is used to define the relationship between objects that can have memberIds and objects
 * that can be memberIds. Objects recognized as having memberIds are CourseOffering, CourseSection
 * and Group, all of which implement the Joinable marker interface. Only a Person object can be a memberId.
 */

@SupportedStatuses({
    Status.ACTIVE,
    Status.DELETED,
    Status.INACTIVE
})

@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "description",
    "member",
    "organization",
    "roles",
    "status",
    "extensions",
    "dateCreated",
    "dateModified" })
public class Membership extends Entity implements org.imsglobal.caliper.entities.w3c.Membership {

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("member")
    //private final Person member;
    private final String memberId;

    @JsonProperty("organization")
    //private final Organization organization;
    private final String organizationId;

    @JsonProperty("roles")
    private final ImmutableList<org.imsglobal.caliper.entities.w3c.Role> roles;

    @JsonProperty("status")
    private final String status;

    /**
     * @param builder apply builder object properties to the Membership object.
     */
    protected Membership(Builder<?> builder) {
        super(builder);

        EntityValidator.checkTypeUri(builder.type, Type.MEMBERSHIP);
        EntityValidator.checkId("memberId", builder.memberId);
        EntityValidator.checkId("organizationId", builder.organizationId);
        EntityValidator.checkMembershipStatus(builder.status);

        this.type = builder.type;
        this.memberId = builder.memberId;
        this.organizationId = builder.organizationId;
        this.roles = ImmutableList.copyOf(builder.roles);
        this.status = builder.status;
    }

    /**
     * @return the type
     */
    @Override
    @Nonnull
    public String getType() {
        return type;
    }

    /**
     * @return the person involved in the memberIdship relationship.
     */
    @Nonnull
    public String getMemberId() {
        return memberId;
    }

    /**
     * @return the membership in which the person is a memberId.
     */
    @Nonnull
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * @return the roles that the agent plays in a memberIdship relationship with an memberIdship.
     */
    @Nullable
    public ImmutableList<org.imsglobal.caliper.entities.w3c.Role> getRoles() {
        return roles;
    }

    /**
     * @return the current status of a membership which applies to all roles.
     */
    @Nonnull
    public String getStatus() {
        return status;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Entity.Builder<T> {
        private String type;
        //private Person member;
        private String memberId;
        //private Organization organization;
        private String organizationId;
        private List<org.imsglobal.caliper.entities.w3c.Role> roles = Lists.newArrayList();
        private String status;

        /**
         * Default Constructor
         */
        public Builder() {
            type(Entity.Type.MEMBERSHIP.uri());
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(String type) {
            this.type = type;
            return self();
        }

        /**
         * @param memberId
         * @return builder.
         */
        public T memberId(String memberId) {
            this.memberId = memberId;
            return self();
        }

        /**
         * @param organizationId
         * @return builder.
         */
        public T organizationId(String organizationId) {
            this.organizationId = organizationId;
            return self();
        }

        /**
         * @param roles
         * @return builder.
         */
        public T roles(List<org.imsglobal.caliper.entities.w3c.Role> roles) {
            this.roles = roles;
            return self();
        }

        /**
         * @param role
         * @return builder.
         */
        public T role(org.imsglobal.caliper.entities.w3c.Role role) {
            this.roles.add(role);
            return self();
        }

        /**
         * @param status
         * @return builder.
         */
        public T status(String status) {
            this.status = status;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the Membership.
         */
        public Membership build() {
            return new Membership(this);
        }
    }

    /**
     *
     */
    private static class Builder2 extends Builder<Builder2> {
        @Override
        protected Builder2 self() {
            return this;
        }
    }

    /**
     * Static factory method.
     * @return a new instance of the builder.
     */
    public static Builder<?> builder() {
        return new Builder2();
    }
}