# TimestreamWriteHelloWorld

for story [PGDFHIR-1894](https://issues.mobilehealth.va.gov/browse/PGDFHIR-1894)



## What does this project do ?

It reads 1 records in database `App.DATABASE_NAME`, in table `App.TABLE_NAME` using region `App.REGION`.

## How to compile

`mvn clean package`

## How to run

Look at the possible problems first, then run.

## Possible problems

* `SdkClientException`
  - `Unable to load AWS credentials ...`
  - You need to set up  `AWS_ACCESS_KEY` and `AWS_SECRET_KEY` with access values. 
  - check *Access Management* in your AWS console to find them.
  - If you don't find them, then you need to create a IAM user, and give him access.
  - You also may need to set AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY as environment variable by: 
    - `vi key_secret_pairs ` and place your AWS_ACCESS_KEY_ID on the first line and AWS_SECRET_ACCESS_KEY on the second line
    - `cat > key_secret_pairs.sh` 
    - `export AWS_ACCESS_KEY_ID=$(head -1 key_secret_pairs | tail -1)` <br />
      `export AWS_SECRET_ACCESS_KEY=$(head -2 key_secret_pairs |tail -1)  `
    - `chmode +wx key_secret_pairs.sh`
    - `. ./key_secret_pairs.sh`
    - Now you can re-run and see if it works
* `ResourceNotFoundException`
  - `The table host_metrics does not exist...`
  - You need to have the table `App.TABLE_NAME` in the database `App.DATABASE_NAME`

## How to run.

`$ java -jar` _<jarFile\>_

where

_<jarFile\>_=`target/TimestreamReadHelloWorld-1.0-SNAPSHOT-jar-with-dependencies.jar`

## Results

should look like:

`The first Item in the Table Norman1 :QueryResponse(QueryId=<QueryId>, Rows=[Row(Data=[Datum(ScalarValue=host_xyz), 
Datum(ScalarValue=zone_xyz), Datum(ScalarValue=region_xyz), Datum(ScalarValue=cpu_utilization), 
Datum(ScalarValue=2024-10-22 19:03:59.005000000), Datum(ScalarValue=13.5)])], ColumnInfo=[ColumnInfo(Name=hostname, 
Type=Type(ScalarType=VARCHAR)), ColumnInfo(Name=az, Type=Type(ScalarType=VARCHAR)), ColumnInfo(Name=region, 
Type=Type(ScalarType=VARCHAR)), ColumnInfo(Name=measure_name, Type=Type(ScalarType=VARCHAR)), ColumnInfo(Name=time, 
Type=Type(ScalarType=TIMESTAMP)), ColumnInfo(Name=measure_value::double, Type=Type(ScalarType=DOUBLE))], 
QueryStatus=QueryStatus(ProgressPercentage=100.0, CumulativeBytesScanned=84, CumulativeBytesMetered=0))
`


## Note

Plugin addition to `pom.xml` (*for executable, fat jar*) <br />
`maven-assembly-plugin`

## Notes from the reading section
* AWS timestream uses a cellular architecture
* Memory storage does fast reads/writes while magnetic stores the data fro longer periods of time based on time 
* Customer partition keys is how users can firther divide the data
* For security, it is important to use IAM permissions, and user groups to only allow the user to use the recourses they need 

