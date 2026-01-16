/*
 * Copyright (C) 2014 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import groovy.xml.XmlSlurper

def project = new XmlSlurper().parseText( new File(basedir, "pom.xml").getText() )
def version = project.version
if ( version == null ){
    version = project.parent.version
}

def groupPath = project.groupId
if ( groupPath == null ){
    groupPath = project.parent.groupId
}

groupPath = groupPath.toString().replace('.', '/')

assert new File( basedir, "target/${project.artifactId}-${version}-project-sources.tar.gz" ).exists()

File dir = new File( localRepositoryPath, "${groupPath}/${project.artifactId}/${version}" )

File tgzFile = new File( dir, "${project.artifactId}-${version}-project-sources.tar.gz")

if ( !tgzFile.exists() )
{
    System.out.println( "Cannot find tar archive: ${tgzFile}" )
    return false
}

File zipFile = new File( dir, "${project.artifactId}-${version}-project-sources.zip")

if ( !zipFile.exists() )
{
    System.out.println( "Cannot find zip archive: ${zipFile}" )
    return false
}

return true
