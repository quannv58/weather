import React from 'react';
import {
    Text,
    View,
    Image,
    Linking
} from 'react-native';
import Card from './Card';
import CardSection from './CardSection';
import Button from './Button';

const AlbumDetail = (props) => {
    return (
        <Card>
            <CardSection>
                <View>
                    <Image 
                        style={styles.thumbnailStyle}
                        source={{ uri: props.record.thumbnail_image }} 
                    />
                </View>

                <View style={styles.headerStyle}>
                    <Text style={styles.headerTextStyle}>{props.record.title}</Text>
                    <Text>{props.record.artist}</Text>
                </View> 
            </CardSection>

            <CardSection>
                <Image 
                style={styles.imageStyle}
                source={{ uri: props.record.image }} 
                />
            </CardSection>

            <CardSection>
                <Button 
                buttonTitle={'Click me!'} 
                onPress={() => Linking.openURL(props.record.url)}
                />
            </CardSection>
        </Card>
    );
};

const styles = {
    headerStyle: {
        flexDirection: 'column',
        justifyContent: 'space-around',
        marginLeft: 10
    },
    headerTextStyle: {
        fontSize: 20
    },
    thumbnailStyle: {
        height: 50,
        width: 50
    },
    thumbnailContainerStyle: {
        justifyContent: 'center',
        alignItems: 'center',
        marginLeft: 10,
        marginRight: 10
    },
    imageStyle: {
        height: 300,
        flex: 1,
        width: null
    }
};

export default AlbumDetail;
