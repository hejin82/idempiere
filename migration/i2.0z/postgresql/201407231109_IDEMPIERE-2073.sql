-- Jul 23, 2014 11:07:39 AM COT
-- IDEMPIERE-2073 AD_Val_Rule_ID is missing from AD_UserDef_Field
UPDATE AD_Field SET IsAdvancedField='Y',Updated=TO_TIMESTAMP('2014-07-23 11:07:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=203266
;

SELECT register_migration_script('201407231109_IDEMPIERE-2073.sql') FROM dual
;

