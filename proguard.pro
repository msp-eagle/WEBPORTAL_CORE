#################################

#################################
-libraryjars <java.home>/jmods
-ignorewarnings
#################################
# Keep DTOs (used across boundary)
#################################
-keep class com.msp.webportal.businesslogic.commonUtil.** { *; }
-keep class com.msp.webportal.businesslogic.constant.** { *; }
-keep class com.msp.webportal.businesslogic.config.** { *; }
-keep class com.msp.webportal.businesslogic.converter.** { *; }
-keep class com.msp.webportal.businesslogic.response.** { *; }
-keep class com.msp.webportal.businesslogic.model.** { *; }
-keep class com.msp.webportal.businesslogic.idrepoEntity.** { *; }
-keep class com.msp.webportal.businesslogic.repository.** { *; }
-keep class com.msp.webportal.businesslogic.idrepoRepository.** { *; }

-keep class com.msp.webportal.businesslogic.exception.** { *; }
-keep class com.msp.webportal.businesslogic.dto.** { *; }
-keep class com.msp.webportal.businesslogic.factory.** { *; }


-keepattributes MethodParameters
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes InnerClasses
-keepattributes EnclosingMethod
-keepattributes Exceptions
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

-keepattributes Signature
#################################
# Keep service facade CLASS NAME
#################################


#-keep class com.msp.webportal.businesslogic.service.** { *; }

#-keep,allowobfuscation class com.msp.webportal.businesslogic.service.about.AboutCareersService
-keep class com.msp.webportal.businesslogic.service.about.AboutCareersService

-keepclassmembers class com.msp.webportal.businesslogic.service.about.AboutCareersService {
  @org.springframework.beans.factory.annotation.Autowired *;
    public com.msp.webportal.businesslogic.model.about.AboutCareers  update(long, com.msp.webportal.businesslogic.model.about.AboutCareers);
     public com.msp.webportal.businesslogic.model.about.AboutCareers save(com.msp.webportal.businesslogic.model.about.AboutCareers);
     public java.util.List list();
     public boolean deleteById(java.lang.Long);
     public com.msp.webportal.businesslogic.model.about.AboutCareers findByid(java.lang.Long);


}


# ===== about.physiscouncil =====
-keep class com.msp.webportal.businesslogic.service.about.phisyscouncil.CompotitionService {
  @org.springframework.beans.factory.annotation.Autowired *;
    public com.msp.webportal.businesslogic.model.about.AboutCareers  update(long, com.msp.webportal.businesslogic.model.about.AboutCareers);
     public com.msp.webportal.businesslogic.model.about.AboutCareers save(com.msp.webportal.businesslogic.model.about.AboutCareers);
     public java.util.List list();
     public boolean deleteById(java.lang.Long);
     public com.msp.webportal.businesslogic.model.about.AboutCareers findByid(java.lang.Long);
 }

-keep class com.msp.webportal.businesslogic.service.about.phisyscouncil.FunctionService {
   @org.springframework.beans.factory.annotation.Autowired *;
     public com.msp.webportal.businesslogic.model.about.AboutCareers  update(long, com.msp.webportal.businesslogic.model.about.AboutCareers);
      public com.msp.webportal.businesslogic.model.about.AboutCareers save(com.msp.webportal.businesslogic.model.about.AboutCareers);
      public java.util.List list();
      public boolean deleteById(java.lang.Long);
      public com.msp.webportal.businesslogic.model.about.AboutCareers findByid(java.lang.Long);
      }


-keep class com.msp.webportal.businesslogic.service.about.phisyscouncil.InteragencyService {
 }
-keep class com.msp.webportal.businesslogic.service.about.phisyscouncil.ResolutionService { *; }
-keep class com.msp.webportal.businesslogic.service.idrepo.IdRepoService { *; }

# ===== about =====
-keep class com.msp.webportal.businesslogic.service.about.AboutCompositionService { *; }
-keep class com.msp.webportal.businesslogic.service.about.AboutDataprivacyService { *; }
-keep class com.msp.webportal.businesslogic.service.about.AboutFunctionsService { *; }
-keep class com.msp.webportal.businesslogic.service.about.AboutInterAgencyService { *; }
-keep class com.msp.webportal.businesslogic.service.about.AboutLegalService { *; }
-keep class com.msp.webportal.businesslogic.service.about.AboutNumbersService { *; }
-keep class com.msp.webportal.businesslogic.service.about.AboutResolutionsService { *; }
-keep class com.msp.webportal.businesslogic.service.about.AboutService { *; }
-keep class com.msp.webportal.businesslogic.service.about.AboutTranceparancyService { *; }

# ===== adminmanagement =====
-keep class com.msp.webportal.businesslogic.service.adminmanagement.AdminManagementService { *; }
-keep class com.msp.webportal.businesslogic.service.adminmanagement.ArchieveService { *; }

# ===== advertisement =====
-keep class com.msp.webportal.businesslogic.service.advertisement.AdvertisementService { *; }

# ===== alyaspcn =====
-keep class com.msp.webportal.businesslogic.service.alyaspcn.AlyaspcnService { *; }

# ===== audit =====
-keep class com.msp.webportal.businesslogic.service.audit.AuthLockService { *; }

# ===== contact =====
-keep class com.msp.webportal.businesslogic.service.contact.BadWordFilterForPhilipine { *; }
-keep class com.msp.webportal.businesslogic.service.contact.ContactService { *; }
-keep class com.msp.webportal.businesslogic.service.contact.ContactServiceImpl { *; }

# ===== crms =====
-keep class com.msp.webportal.businesslogic.service.crms.CRMSService { *; }
-keep class com.msp.webportal.businesslogic.service.crms.FeedbackDecal { *; }
-keep class com.msp.webportal.businesslogic.service.crms.FeedbackDef { *; }

#=== ephilid
-keep class com.msp.webportal.businesslogic.service.ephilid.EphilfaqService { *; }
-keep class com.msp.webportal.businesslogic.service.ephilid.EphilFaqServiceimpl { *; }
-keep class com.msp.webportal.businesslogic.service.ephilid.EphilidService { *; }

#====event
-keep class com.msp.webportal.businesslogic.service.event.EventService { *; }


#====faq
-keep class com.msp.webportal.businesslogic.service.faq.FaqAnswerService { *; }
-keep class com.msp.webportal.businesslogic.service.faq.FaqAnswerServiceimpl { *; }
-keep class com.msp.webportal.businesslogic.service.faq.FaqQuestionService { *; }
-keep class com.msp.webportal.businesslogic.service.faq.FaqQuestionServiceimpl { *; }



#====knowledge
-keep class com.msp.webportal.businesslogic.service.knowledge.DocumentService { *; }
-keep class com.msp.webportal.businesslogic.service.knowledge.MediaService { *; }
-keep class com.msp.webportal.businesslogic.service.knowledge.PoliciesService { *; }
-keep class com.msp.webportal.businesslogic.service.knowledge.ProceduresService { *; }
-keep class com.msp.webportal.businesslogic.service.knowledge.ResourcesService { *; }


#====login
-keep class com.msp.webportal.businesslogic.service.login.CaptchaService { *; }
-keep class com.msp.webportal.businesslogic.service.login.CaptchaServiceimpl { *; }
-keep class com.msp.webportal.businesslogic.service.login.HttpRequestExtractor { *; }
-keep class com.msp.webportal.businesslogic.service.login.LoginService { *; }
-keep class com.msp.webportal.businesslogic.service.login.MacAddress { *; }


#====loststolen
-keep class com.msp.webportal.businesslogic.service.loststolen.LostStolenService { *; }

#========notifier
-keep class com.msp.webportal.businesslogic.service.notifier.EmailNotifier { *; }


#====partneranddevice
 -keep class com.msp.webportal.businesslogic.service.partneranddevice.AuthdevService { *; }
 -keep class com.msp.webportal.businesslogic.service.partneranddevice.AuthdocsService { *; }
 -keep class com.msp.webportal.businesslogic.service.partneranddevice.BiodevService { *; }
 -keep class com.msp.webportal.businesslogic.service.partneranddevice.BioPartnerService { *; }
 -keep class com.msp.webportal.businesslogic.service.partneranddevice.DevsecService { *; }
 -keep class com.msp.webportal.businesslogic.service.partneranddevice.EkycService { *; }
 -keep class com.msp.webportal.businesslogic.service.partneranddevice.OpmService { *; }
 -keep class com.msp.webportal.businesslogic.service.partneranddevice.QrService { *; }
 -keep class com.msp.webportal.businesslogic.service.partneranddevice.RpService { *; }
 -keep class com.msp.webportal.businesslogic.service.partneranddevice.TSPService { *; }



#===philysyscheck
-keep class com.msp.webportal.businesslogic.service.philysyscheck.PhilypsService { *; }

#===registerprocess
-keep class com.msp.webportal.businesslogic.service.registerprocess.RegisterMyidService {
   @org.springframework.beans.factory.annotation.Autowired *;
     public com.msp.webportal.businesslogic.model.registerprocess.RegisterMyid  update(long, com.msp.webportal.businesslogic.model.registerprocess.RegisterMyid);
      public com.msp.webportal.businesslogic.model.registerprocess.RegisterMyid save(com.msp.webportal.businesslogic.model.registerprocess.RegisterMyid);
      public java.util.List list();
      public boolean deleteById(java.lang.Long);
      public com.msp.webportal.businesslogic.model.registerprocess.RegisterMyids findByid(java.lang.Long);
 }
-keep class com.msp.webportal.businesslogic.service.registerprocess.RegisterProcessService {
    @org.springframework.beans.factory.annotation.Autowired *;
      public com.msp.webportal.businesslogic.model.registerprocess.Registerprocess  update(long,  com.msp.webportal.businesslogic.model.registerprocess.Registerprocess);
       public  com.msp.webportal.businesslogic.model.registerprocess.Registerprocess save( com.msp.webportal.businesslogic.model.registerprocess.Registerprocess);
       public java.util.List list();
       public boolean deleteById(java.lang.Long);
       public  com.msp.webportal.businesslogic.model.registerprocess.Registerprocess findByid(java.lang.Long);
 }


-keep class com.msp.webportal.businesslogic.service.registerprocess.RegistersteponeService {
   @org.springframework.beans.factory.annotation.Autowired *;
      public com.msp.webportal.businesslogic.model.registerprocess.Registersterpone  update(long, com.msp.webportal.businesslogic.model.registerprocess.Registersterpone);
       public com.msp.webportal.businesslogic.model.registerprocess.Registersterpone save(com.msp.webportal.businesslogic.model.registerprocess.Registersterpone);
       public java.util.List list();
       public boolean deleteById(java.lang.Long);
       public com.msp.webportal.businesslogic.model.registerprocess.Registersterpone findByid(java.lang.Long);
 }



-keep class com.msp.webportal.businesslogic.service.registerprocess.RegistersteptwoService {
   @org.springframework.beans.factory.annotation.Autowired *;
      public com.msp.webportal.businesslogic.model.registerprocess.Registersteptwo  update(long, com.msp.webportal.businesslogic.model.registerprocess.Registersteptwo);
       public com.msp.webportal.businesslogic.model.registerprocess.Registersteptwo save(com.msp.webportal.businesslogic.model.registerprocess.Registersteptwo);
       public java.util.List list();
       public boolean deleteById(java.lang.Long);
       public com.msp.webportal.businesslogic.model.registerprocess.Registersteptwo findByid(java.lang.Long);
 }


-keep class com.msp.webportal.businesslogic.service.registerprocess.SupportDocumentService {
   @org.springframework.beans.factory.annotation.Autowired *;
      public com.msp.webportal.businesslogic.model.registerprocess.Supportdocument
      update(long, com.msp.webportal.businesslogic.model.registerprocess.Supportdocument);
       public com.msp.webportal.businesslogic.model.registerprocess.Supportdocument save(com.msp.webportal.businesslogic.model.registerprocess.Supportdocument);
       public java.util.List list();
       public boolean deleteById(java.lang.Long);
       public com.msp.webportal.businesslogic.model.registerprocess.Supportdocument findByid(java.lang.Long);

 }



#=====sms
-keep class com.msp.webportal.businesslogic.service.sms.SmsNotificationService { *; }


#====testmonial
-keep class com.msp.webportal.businesslogic.service.testimonial.TestimonialService { *; }

#=====home
-keep class com.msp.webportal.businesslogic.service.home.HomeService { *; }
-keep class com.msp.webportal.businesslogic.service.home.HomegraphService { *; }


#===trackStatus
-keep class com.msp.webportal.businesslogic.service.trackStatus.AuthStatusService { *; }
-keep class com.msp.webportal.businesslogic.service.trackStatus.OtpManagerService { *; }
-keep class com.msp.webportal.businesslogic.service.trackStatus.RecordHistoryService { *; }
-keep class com.msp.webportal.businesslogic.service.trackStatus.ResidentOtpService { *; }
-keep class com.msp.webportal.businesslogic.service.trackStatus.TrackStatusService { *; }



#===updatedemodata
-keep class com.msp.webportal.businesslogic.service.updatedemodata.UpdatedemodataService { *; }


#====usecase
-keep class com.msp.webportal.businesslogic.service.usecase.UsecaseService { *; }









#################################
# Ignore framework warnings
#################################
-dontwarn java.**
-dontwarn javax.**
-dontwarn org.springframework.**
-dontwarn com.fasterxml.jackson.**
-dontwarn io.jsonwebtoken.**

#################################
# Obfuscation (AGGRESSIVE)
#################################
-allowaccessmodification
#-overloadaggressively
#-repackageclasses ''
#-flattenpackagehierarchy
#-dontusemixedcaseclassnames

#################################
# Remove debug info
#################################
#-renamesourcefileattribute SourceFile

# KEEP SPRING FRAMEWORK
#################################
-keep class org.springframework.** { *; }
-keep class javax.** { *; }

#################################
# KEEP YOUR APPLICATION PACKAGE
#################################


#################################
# KEEP SPRING BOOT LOADER
#################################
-keep class org.springframework.boot.loader.** { *; }

# Keep all System.out / System.err print methods
-keep class java.io.PrintStream {
    public void println(...);
    public void print(...);
    public void printf(...);
    public void format(...);
}
