package jp.mirageworld.spring.oauth2.azure.service;

import java.time.ZoneOffset;
import java.util.List;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

import com.azure.core.credential.AccessToken;
import com.azure.core.credential.TokenCredential;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.externalconnectors.requests.ExternalConnectionCollectionRequestBuilder;
import com.microsoft.graph.externalconnectors.requests.ExternalConnectionRequestBuilder;
import com.microsoft.graph.externalconnectors.requests.ExternalRequestBuilder;
import com.microsoft.graph.requests.*;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public final class GraphService {

    private final OAuth2AuthorizedClient graph;

    static final List<String> scopes = List.of("https://graph.microsoft.com/.default");

    /**
     * client 作成.
     */
    GraphServiceClient<Request> client() {
        log.debug("make client");
        AccessToken at = new AccessToken(
                this.graph.getAccessToken().getTokenValue(),
                this.graph.getAccessToken().getExpiresAt().atOffset(ZoneOffset.ofHours(9)));

        TokenCredential credential = request -> Mono.justOrEmpty(at);
        TokenCredentialAuthProvider provider = new TokenCredentialAuthProvider(scopes, credential);

        return GraphServiceClient.builder()
                .authenticationProvider(provider)
                .buildClient();
    }

    /**
     * Gets the collection of Invitations objects
     *
     * @return the request builder for the collection of Invitations objects
     */
    @Nonnull
    public InvitationCollectionRequestBuilder invitations() {
        return this.client().invitations();
    }

    /**
     * Gets a single Invitations
     *
     * @param id the id of the Invitations to retrieve
     * @return the request builder for the Invitations object
     */
    @Nonnull
    public InvitationRequestBuilder invitations(@Nonnull final String id) {
        return this.client().invitations(id);
    }

    /**
     * Gets the collection of Users objects
     *
     * @return the request builder for the collection of Users objects
     */
    @Nonnull
    public UserCollectionRequestBuilder users() {
        return this.client().users();
    }

    /**
     * Gets a single Users
     *
     * @param id the id of the Users to retrieve
     * @return the request builder for the Users object
     */
    @Nonnull
    public UserRequestBuilder users(@Nonnull final String id) {
        return this.client().users(id);
    }

    /**
     * Gets the collection of ApplicationTemplates objects
     *
     * @return the request builder for the collection of ApplicationTemplates objects
     */
    @Nonnull
    public ApplicationTemplateCollectionRequestBuilder applicationTemplates() {
        return this.client().applicationTemplates();
    }

    /**
     * Gets a single ApplicationTemplates
     *
     * @param id the id of the ApplicationTemplates to retrieve
     * @return the request builder for the ApplicationTemplates object
     */
    @Nonnull
    public ApplicationTemplateRequestBuilder applicationTemplates(@Nonnull final String id) {
        return this.client().applicationTemplates(id);
    }

    /**
     * Gets the collection of AuthenticationMethodConfigurations objects
     *
     * @return the request builder for the collection of AuthenticationMethodConfigurations objects
     */
    @Nonnull
    public AuthenticationMethodConfigurationCollectionRequestBuilder authenticationMethodConfigurations() {
        return this.client().authenticationMethodConfigurations();
    }

    /**
     * Gets a single AuthenticationMethodConfigurations
     *
     * @param id the id of the AuthenticationMethodConfigurations to retrieve
     * @return the request builder for the AuthenticationMethodConfigurations object
     */
    @Nonnull
    public AuthenticationMethodConfigurationRequestBuilder //
            authenticationMethodConfigurations(@Nonnull final String id) {
        return this.client().authenticationMethodConfigurations(id);
    }

    /**
     * Gets the collection of Applications objects
     *
     * @return the request builder for the collection of Applications objects
     */
    @Nonnull
    public ApplicationCollectionRequestBuilder applications() {
        return this.client().applications();
    }

    /**
     * Gets a single Applications
     *
     * @param id the id of the Applications to retrieve
     * @return the request builder for the Applications object
     */
    @Nonnull
    public ApplicationRequestBuilder applications(@Nonnull final String id) {
        return this.client().applications(id);
    }

    /**
     * Gets the collection of CertificateBasedAuthConfiguration objects
     *
     * @return the request builder for the collection of CertificateBasedAuthConfiguration objects
     */
    @Nonnull
    public CertificateBasedAuthConfigurationCollectionRequestBuilder certificateBasedAuthConfiguration() {
        return this.client().certificateBasedAuthConfiguration();
    }

    /**
     * Gets a single CertificateBasedAuthConfiguration
     *
     * @param id the id of the CertificateBasedAuthConfiguration to retrieve
     * @return the request builder for the CertificateBasedAuthConfiguration object
     */
    @Nonnull
    public CertificateBasedAuthConfigurationRequestBuilder certificateBasedAuthConfiguration(@Nonnull final String id) {
        return this.client().certificateBasedAuthConfiguration(id);
    }

    /**
     * Gets the collection of Contacts objects
     *
     * @return the request builder for the collection of Contacts objects
     */
    @Nonnull
    public OrgContactCollectionRequestBuilder contacts() {
        return this.client().contacts();
    }

    /**
     * Gets a single Contacts
     *
     * @param id the id of the Contacts to retrieve
     * @return the request builder for the Contacts object
     */
    @Nonnull
    public OrgContactRequestBuilder contacts(@Nonnull final String id) {
        return this.client().contacts(id);
    }

    /**
     * Gets the collection of Contracts objects
     *
     * @return the request builder for the collection of Contracts objects
     */
    @Nonnull
    public ContractCollectionRequestBuilder contracts() {
        return this.client().contracts();
    }

    /**
     * Gets a single Contracts
     *
     * @param id the id of the Contracts to retrieve
     * @return the request builder for the Contracts object
     */
    @Nonnull
    public ContractRequestBuilder contracts(@Nonnull final String id) {
        return this.client().contracts(id);
    }

    /**
     * Gets the collection of Devices objects
     *
     * @return the request builder for the collection of Devices objects
     */
    @Nonnull
    public DeviceCollectionRequestBuilder devices() {
        return this.client().devices();
    }

    /**
     * Gets a single Devices
     *
     * @param id the id of the Devices to retrieve
     * @return the request builder for the Devices object
     */
    @Nonnull
    public DeviceRequestBuilder devices(@Nonnull final String id) {
        return this.client().devices(id);
    }

    /**
     * Gets the collection of DirectoryObjects objects
     *
     * @return the request builder for the collection of DirectoryObjects objects
     */
    @Nonnull
    public DirectoryObjectCollectionRequestBuilder directoryObjects() {
        return this.client().directoryObjects();
    }

    /**
     * Gets a single DirectoryObjects
     *
     * @param id the id of the DirectoryObjects to retrieve
     * @return the request builder for the DirectoryObjects object
     */
    @Nonnull
    public DirectoryObjectRequestBuilder directoryObjects(@Nonnull final String id) {
        return this.client().directoryObjects(id);
    }

    /**
     * Gets the collection of DirectoryRoles objects
     *
     * @return the request builder for the collection of DirectoryRoles objects
     */
    @Nonnull
    public DirectoryRoleCollectionRequestBuilder directoryRoles() {
        return this.client().directoryRoles();
    }

    /**
     * Gets a single DirectoryRoles
     *
     * @param id the id of the DirectoryRoles to retrieve
     * @return the request builder for the DirectoryRoles object
     */
    @Nonnull
    public DirectoryRoleRequestBuilder directoryRoles(@Nonnull final String id) {
        return this.client().directoryRoles(id);
    }

    /**
     * Gets the collection of DirectoryRoleTemplates objects
     *
     * @return the request builder for the collection of DirectoryRoleTemplates objects
     */
    @Nonnull
    public DirectoryRoleTemplateCollectionRequestBuilder directoryRoleTemplates() {
        return this.client().directoryRoleTemplates();
    }

    /**
     * Gets a single DirectoryRoleTemplates
     *
     * @param id the id of the DirectoryRoleTemplates to retrieve
     * @return the request builder for the DirectoryRoleTemplates object
     */
    @Nonnull
    public DirectoryRoleTemplateRequestBuilder directoryRoleTemplates(@Nonnull final String id) {
        return this.client().directoryRoleTemplates(id);
    }

    /**
     * Gets the collection of DomainDnsRecords objects
     *
     * @return the request builder for the collection of DomainDnsRecords objects
     */
    @Nonnull
    public DomainDnsRecordCollectionRequestBuilder domainDnsRecords() {
        return this.client().domainDnsRecords();
    }

    /**
     * Gets a single DomainDnsRecords
     *
     * @param id the id of the DomainDnsRecords to retrieve
     * @return the request builder for the DomainDnsRecords object
     */
    @Nonnull
    public DomainDnsRecordRequestBuilder domainDnsRecords(@Nonnull final String id) {
        return this.client().domainDnsRecords(id);
    }

    /**
     * Gets the collection of Domains objects
     *
     * @return the request builder for the collection of Domains objects
     */
    @Nonnull
    public DomainCollectionRequestBuilder domains() {
        return this.client().domains();
    }

    /**
     * Gets a single Domains
     *
     * @param id the id of the Domains to retrieve
     * @return the request builder for the Domains object
     */
    @Nonnull
    public DomainRequestBuilder domains(@Nonnull final String id) {
        return this.client().domains(id);
    }

    /**
     * Gets the collection of Groups objects
     *
     * @return the request builder for the collection of Groups objects
     */
    @Nonnull
    public GroupCollectionRequestBuilder groups() {
        return this.client().groups();
    }

    /**
     * Gets a single Groups
     *
     * @param id the id of the Groups to retrieve
     * @return the request builder for the Groups object
     */
    @Nonnull
    public GroupRequestBuilder groups(@Nonnull final String id) {
        return this.client().groups(id);
    }

    /**
     * Gets the collection of GroupSettings objects
     *
     * @return the request builder for the collection of GroupSettings objects
     */
    @Nonnull
    public GroupSettingCollectionRequestBuilder groupSettings() {
        return this.client().groupSettings();
    }

    /**
     * Gets a single GroupSettings
     *
     * @param id the id of the GroupSettings to retrieve
     * @return the request builder for the GroupSettings object
     */
    @Nonnull
    public GroupSettingRequestBuilder groupSettings(@Nonnull final String id) {
        return this.client().groupSettings(id);
    }

    /**
     * Gets the collection of GroupSettingTemplates objects
     *
     * @return the request builder for the collection of GroupSettingTemplates objects
     */
    @Nonnull
    public GroupSettingTemplateCollectionRequestBuilder groupSettingTemplates() {
        return this.client().groupSettingTemplates();
    }

    /**
     * Gets a single GroupSettingTemplates
     *
     * @param id the id of the GroupSettingTemplates to retrieve
     * @return the request builder for the GroupSettingTemplates object
     */
    @Nonnull
    public GroupSettingTemplateRequestBuilder groupSettingTemplates(@Nonnull final String id) {
        return this.client().groupSettingTemplates(id);
    }

    /**
     * Gets the collection of Localizations objects
     *
     * @return the request builder for the collection of Localizations objects
     */
    @Nonnull
    public OrganizationalBrandingLocalizationCollectionRequestBuilder localizations() {
        return this.client().localizations();
    }

    /**
     * Gets a single Localizations
     *
     * @param id the id of the Localizations to retrieve
     * @return the request builder for the Localizations object
     */
    @Nonnull
    public OrganizationalBrandingLocalizationRequestBuilder localizations(@Nonnull final String id) {
        return this.client().localizations(id);
    }

    /**
     * Gets the collection of Oauth2PermissionGrants objects
     *
     * @return the request builder for the collection of Oauth2PermissionGrants objects
     */
    @Nonnull
    public OAuth2PermissionGrantCollectionRequestBuilder oauth2PermissionGrants() {
        return this.client().oauth2PermissionGrants();
    }

    /**
     * Gets a single Oauth2PermissionGrants
     *
     * @param id the id of the Oauth2PermissionGrants to retrieve
     * @return the request builder for the Oauth2PermissionGrants object
     */
    @Nonnull
    public OAuth2PermissionGrantRequestBuilder oauth2PermissionGrants(@Nonnull final String id) {
        return this.client().oauth2PermissionGrants(id);
    }

    /**
     * Gets the collection of Organization objects
     *
     * @return the request builder for the collection of Organization objects
     */
    @Nonnull
    public OrganizationCollectionRequestBuilder organization() {
        return this.client().organization();
    }

    /**
     * Gets a single Organization
     *
     * @param id the id of the Organization to retrieve
     * @return the request builder for the Organization object
     */
    @Nonnull
    public OrganizationRequestBuilder organization(@Nonnull final String id) {
        return this.client().organization(id);
    }

    /**
     * Gets the collection of PermissionGrants objects
     *
     * @return the request builder for the collection of PermissionGrants objects
     */
    @Nonnull
    public ResourceSpecificPermissionGrantCollectionRequestBuilder permissionGrants() {
        return this.client().permissionGrants();
    }

    /**
     * Gets a single PermissionGrants
     *
     * @param id the id of the PermissionGrants to retrieve
     * @return the request builder for the PermissionGrants object
     */
    @Nonnull
    public ResourceSpecificPermissionGrantRequestBuilder permissionGrants(@Nonnull final String id) {
        return this.client().permissionGrants(id);
    }

    /**
     * Gets the collection of ScopedRoleMemberships objects
     *
     * @return the request builder for the collection of ScopedRoleMemberships objects
     */
    @Nonnull
    public ScopedRoleMembershipCollectionRequestBuilder scopedRoleMemberships() {
        return this.client().scopedRoleMemberships();
    }

    /**
     * Gets a single ScopedRoleMemberships
     *
     * @param id the id of the ScopedRoleMemberships to retrieve
     * @return the request builder for the ScopedRoleMemberships object
     */
    @Nonnull
    public ScopedRoleMembershipRequestBuilder scopedRoleMemberships(@Nonnull final String id) {
        return this.client().scopedRoleMemberships(id);
    }

    /**
     * Gets the collection of ServicePrincipals objects
     *
     * @return the request builder for the collection of ServicePrincipals objects
     */
    @Nonnull
    public ServicePrincipalCollectionRequestBuilder servicePrincipals() {
        return this.client().servicePrincipals();
    }

    /**
     * Gets a single ServicePrincipals
     *
     * @param id the id of the ServicePrincipals to retrieve
     * @return the request builder for the ServicePrincipals object
     */
    @Nonnull
    public ServicePrincipalRequestBuilder servicePrincipals(@Nonnull final String id) {
        return this.client().servicePrincipals(id);
    }

    /**
     * Gets the collection of SubscribedSkus objects
     *
     * @return the request builder for the collection of SubscribedSkus objects
     */
    @Nonnull
    public SubscribedSkuCollectionRequestBuilder subscribedSkus() {
        return this.client().subscribedSkus();
    }

    /**
     * Gets a single SubscribedSkus
     *
     * @param id the id of the SubscribedSkus to retrieve
     * @return the request builder for the SubscribedSkus object
     */
    @Nonnull
    public SubscribedSkuRequestBuilder subscribedSkus(@Nonnull final String id) {
        return this.client().subscribedSkus(id);
    }

    /**
     * Gets the collection of Places objects
     *
     * @return the request builder for the collection of Places objects
     */
    @Nonnull
    public PlaceCollectionRequestBuilder places() {
        return this.client().places();
    }

    /**
     * Gets a single Places
     *
     * @param id the id of the Places to retrieve
     * @return the request builder for the Places object
     */
    @Nonnull
    public PlaceRequestBuilder places(@Nonnull final String id) {
        return this.client().places(id);
    }

    /**
     * Gets the collection of Drives objects
     *
     * @return the request builder for the collection of Drives objects
     */
    @Nonnull
    public DriveCollectionRequestBuilder drives() {
        return this.client().drives();
    }

    /**
     * Gets a single Drives
     *
     * @param id the id of the Drives to retrieve
     * @return the request builder for the Drives object
     */
    @Nonnull
    public DriveRequestBuilder drives(@Nonnull final String id) {
        return this.client().drives(id);
    }

    /**
     * Gets the collection of Shares objects
     *
     * @return the request builder for the collection of Shares objects
     */
    @Nonnull
    public SharedDriveItemCollectionRequestBuilder shares() {
        return this.client().shares();
    }

    /**
     * Gets a single Shares
     *
     * @param id the id of the Shares to retrieve
     * @return the request builder for the Shares object
     */
    @Nonnull
    public SharedDriveItemRequestBuilder shares(@Nonnull final String id) {
        return this.client().shares(id);
    }

    /**
     * Gets the collection of Sites objects
     *
     * @return the request builder for the collection of Sites objects
     */
    @Nonnull
    public SiteCollectionRequestBuilder sites() {
        return this.client().sites();
    }

    /**
     * Gets a single Sites
     *
     * @param id the id of the Sites to retrieve
     * @return the request builder for the Sites object
     */
    @Nonnull
    public SiteRequestBuilder sites(@Nonnull final String id) {
        return this.client().sites(id);
    }

    /**
     * Gets the collection of SchemaExtensions objects
     *
     * @return the request builder for the collection of SchemaExtensions objects
     */
    @Nonnull
    public SchemaExtensionCollectionRequestBuilder schemaExtensions() {
        return this.client().schemaExtensions();
    }

    /**
     * Gets a single SchemaExtensions
     *
     * @param id the id of the SchemaExtensions to retrieve
     * @return the request builder for the SchemaExtensions object
     */
    @Nonnull
    public SchemaExtensionRequestBuilder schemaExtensions(@Nonnull final String id) {
        return this.client().schemaExtensions(id);
    }

    /**
     * Gets the collection of GroupLifecyclePolicies objects
     *
     * @return the request builder for the collection of GroupLifecyclePolicies objects
     */
    @Nonnull
    public GroupLifecyclePolicyCollectionRequestBuilder groupLifecyclePolicies() {
        return this.client().groupLifecyclePolicies();
    }

    /**
     * Gets a single GroupLifecyclePolicies
     *
     * @param id the id of the GroupLifecyclePolicies to retrieve
     * @return the request builder for the GroupLifecyclePolicies object
     */
    @Nonnull
    public GroupLifecyclePolicyRequestBuilder groupLifecyclePolicies(@Nonnull final String id) {
        return this.client().groupLifecyclePolicies(id);
    }

    /**
     * Gets the collection of AgreementAcceptances objects
     *
     * @return the request builder for the collection of AgreementAcceptances objects
     */
    @Nonnull
    public AgreementAcceptanceCollectionRequestBuilder agreementAcceptances() {
        return this.client().agreementAcceptances();
    }

    /**
     * Gets a single AgreementAcceptances
     *
     * @param id the id of the AgreementAcceptances to retrieve
     * @return the request builder for the AgreementAcceptances object
     */
    @Nonnull
    public AgreementAcceptanceRequestBuilder agreementAcceptances(@Nonnull final String id) {
        return this.client().agreementAcceptances(id);
    }

    /**
     * Gets the collection of Agreements objects
     *
     * @return the request builder for the collection of Agreements objects
     */
    @Nonnull
    public AgreementCollectionRequestBuilder agreements() {
        return this.client().agreements();
    }

    /**
     * Gets a single Agreements
     *
     * @param id the id of the Agreements to retrieve
     * @return the request builder for the Agreements object
     */
    @Nonnull
    public AgreementRequestBuilder agreements(@Nonnull final String id) {
        return this.client().agreements(id);
    }

    /**
     * Gets the collection of DataPolicyOperations objects
     *
     * @return the request builder for the collection of DataPolicyOperations objects
     */
    @Nonnull
    public DataPolicyOperationCollectionRequestBuilder dataPolicyOperations() {
        return this.client().dataPolicyOperations();
    }

    /**
     * Gets a single DataPolicyOperations
     *
     * @param id the id of the DataPolicyOperations to retrieve
     * @return the request builder for the DataPolicyOperations object
     */
    @Nonnull
    public DataPolicyOperationRequestBuilder dataPolicyOperations(@Nonnull final String id) {
        return this.client().dataPolicyOperations(id);
    }

    /**
     * Gets the collection of Subscriptions objects
     *
     * @return the request builder for the collection of Subscriptions objects
     */
    @Nonnull
    public SubscriptionCollectionRequestBuilder subscriptions() {
        return this.client().subscriptions();
    }

    /**
     * Gets a single Subscriptions
     *
     * @param id the id of the Subscriptions to retrieve
     * @return the request builder for the Subscriptions object
     */
    @Nonnull
    public SubscriptionRequestBuilder subscriptions(@Nonnull final String id) {
        return this.client().subscriptions(id);
    }

    /**
     * Gets the collection of Connections objects
     *
     * @return the request builder for the collection of Connections objects
     */
    @Nonnull
    public ExternalConnectionCollectionRequestBuilder connections() {
        return this.client().connections();
    }

    /**
     * Gets a single Connections
     *
     * @param id the id of the Connections to retrieve
     * @return the request builder for the Connections object
     */
    @Nonnull
    public ExternalConnectionRequestBuilder connections(@Nonnull final String id) {
        return this.client().connections(id);
    }

    /**
     * Gets the collection of Chats objects
     *
     * @return the request builder for the collection of Chats objects
     */
    @Nonnull
    public ChatCollectionRequestBuilder chats() {
        return this.client().chats();
    }

    /**
     * Gets a single Chats
     *
     * @param id the id of the Chats to retrieve
     * @return the request builder for the Chats object
     */
    @Nonnull
    public ChatRequestBuilder chats(@Nonnull final String id) {
        return this.client().chats(id);
    }

    /**
     * Gets the collection of Teams objects
     *
     * @return the request builder for the collection of Teams objects
     */
    @Nonnull
    public TeamCollectionRequestBuilder teams() {
        return this.client().teams();
    }

    /**
     * Gets a single Teams
     *
     * @param id the id of the Teams to retrieve
     * @return the request builder for the Teams object
     */
    @Nonnull
    public TeamRequestBuilder teams(@Nonnull final String id) {
        return this.client().teams(id);
    }

    /**
     * Gets the collection of TeamsTemplates objects
     *
     * @return the request builder for the collection of TeamsTemplates objects
     */
    @Nonnull
    public TeamsTemplateCollectionRequestBuilder teamsTemplates() {
        return this.client().teamsTemplates();
    }

    /**
     * Gets a single TeamsTemplates
     *
     * @param id the id of the TeamsTemplates to retrieve
     * @return the request builder for the TeamsTemplates object
     */
    @Nonnull
    public TeamsTemplateRequestBuilder teamsTemplates(@Nonnull final String id) {
        return this.client().teamsTemplates(id);
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the AuditLogRoot
     */
    @Nonnull
    public AuditLogRootRequestBuilder auditLogs() {
        return this.client().auditLogs();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the AuthenticationMethodsPolicy
     */
    @Nonnull
    public AuthenticationMethodsPolicyRequestBuilder authenticationMethodsPolicy() {
        return this.client().authenticationMethodsPolicy();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the SolutionsRoot
     */
    @Nonnull
    public SolutionsRootRequestBuilder solutions() {
        return this.client().solutions();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the Privacy
     */
    @Nonnull
    public PrivacyRequestBuilder privacy() {
        return this.client().privacy();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the Compliance
     */
    @Nonnull
    public ComplianceRequestBuilder compliance() {
        return this.client().compliance();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the Security
     */
    @Nonnull
    public SecurityRequestBuilder security() {
        return this.client().security();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the IdentityContainer
     */
    @Nonnull
    public IdentityContainerRequestBuilder identity() {
        return this.client().identity();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the OrganizationalBranding
     */
    @Nonnull
    public OrganizationalBrandingRequestBuilder branding() {
        return this.client().branding();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the Directory
     */
    @Nonnull
    public DirectoryRequestBuilder directory() {
        return this.client().directory();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the User
     */
    @Nonnull
    public UserRequestBuilder me() {
        return this.client().me();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the PolicyRoot
     */
    @Nonnull
    public PolicyRootRequestBuilder policies() {
        return this.client().policies();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the EducationRoot
     */
    @Nonnull
    public EducationRootRequestBuilder education() {
        return this.client().education();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the RoleManagement
     */
    @Nonnull
    public RoleManagementRequestBuilder roleManagement() {
        return this.client().roleManagement();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the Drive
     */
    @Nonnull
    public DriveRequestBuilder drive() {
        return this.client().drive();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the CloudCommunications
     */
    @Nonnull
    public CloudCommunicationsRequestBuilder communications() {
        return this.client().communications();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the IdentityGovernance
     */
    @Nonnull
    public IdentityGovernanceRequestBuilder identityGovernance() {
        return this.client().identityGovernance();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the IdentityProtectionRoot
     */
    @Nonnull
    public IdentityProtectionRootRequestBuilder identityProtection() {
        return this.client().identityProtection();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the DeviceAppManagement
     */
    @Nonnull
    public DeviceAppManagementRequestBuilder deviceAppManagement() {
        return this.client().deviceAppManagement();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the DeviceManagement
     */
    @Nonnull
    public DeviceManagementRequestBuilder deviceManagement() {
        return this.client().deviceManagement();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the ReportRoot
     */
    @Nonnull
    public ReportRootRequestBuilder reports() {
        return this.client().reports();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the Admin
     */
    @Nonnull
    public AdminRequestBuilder admin() {
        return this.client().admin();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the SearchEntity
     */
    @Nonnull
    public SearchEntityRequestBuilder search() {
        return this.client().search();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the Planner
     */
    @Nonnull
    public PlannerRequestBuilder planner() {
        return this.client().planner();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the TenantRelationship
     */
    @Nonnull
    public TenantRelationshipRequestBuilder tenantRelationships() {
        return this.client().tenantRelationships();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the Print
     */
    @Nonnull
    public PrintRequestBuilder print() {
        return this.client().print();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the External
     */
    @Nonnull
    public ExternalRequestBuilder external() {
        return this.client().external();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the AppCatalogs
     */
    @Nonnull
    public AppCatalogsRequestBuilder appCatalogs() {
        return this.client().appCatalogs();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the Teamwork
     */
    @Nonnull
    public TeamworkRequestBuilder teamwork() {
        return this.client().teamwork();
    }

    /**
     * Gets the GraphServiceRequestBuilder
     *
     * @return the InformationProtection
     */
    @Nonnull
    public InformationProtectionRequestBuilder informationProtection() {
        return this.client().informationProtection();
    }
}
